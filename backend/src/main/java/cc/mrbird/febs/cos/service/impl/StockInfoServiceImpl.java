package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.cos.entity.ArchivesInfo;
import cc.mrbird.febs.cos.entity.GoodsBelong;
import cc.mrbird.febs.cos.entity.StockInfo;
import cc.mrbird.febs.cos.dao.StockInfoMapper;
import cc.mrbird.febs.cos.entity.StockPut;
import cc.mrbird.febs.cos.service.*;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockInfoServiceImpl extends ServiceImpl<StockInfoMapper, StockInfo> implements IStockInfoService {

    private final IStockPutService stockPutService;

    private final IGoodsBelongService goodsBelongService;

    private final IBulletinInfoService bulletinInfoService;

    private final IArchivesInfoService archivesInfoService;

    private final StockInfoMapper stockInfoMapper;

    @Override
    public IPage<LinkedHashMap<String, Object>> stockInfoByPage(Page page, StockInfo stockInfo) {
        return baseMapper.stockInfoByPage(page, stockInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean stockPut(String goods, String custodian, String putUser, String content, BigDecimal price) throws FebsException {
        // 添加入库单
        StockPut stockPut = new StockPut();
        stockPut.setContent(content);
        stockPut.setCreateDate(DateUtil.formatDateTime(new Date()));
        stockPut.setCustodian(custodian);
        stockPut.setPutUser(putUser);
        stockPut.setPrice(price);
        stockPut.setNum("PUT-" + System.currentTimeMillis());
        stockPutService.save(stockPut);

        // 添加入库
        JSONArray array = JSONUtil.parseArray(goods);
        List<GoodsBelong> goodsBelongList = JSONUtil.toList(array, GoodsBelong.class);

        if (this.checkStock(goodsBelongList)) {
            throw new FebsException("库房数量不能超过1000");
        }

        goodsBelongList.forEach(item -> {
            item.setCreateDate(DateUtil.formatDateTime(new Date()));
            item.setNum(stockPut.getNum());
            // 判断库房物品是否存在
            StockInfo stockInfo = this.getOne(Wrappers.<StockInfo>lambdaQuery().eq(StockInfo::getName, item.getName()).eq(StockInfo::getTypeId, item.getTypeId()).eq(StockInfo::getIsIn, 0));
            if (stockInfo != null) {
                // 更改库房数据
                this.update(Wrappers.<StockInfo>lambdaUpdate().set(StockInfo::getAmount, stockInfo.getAmount()+item.getAmount())
                        .set(StockInfo::getPrice, stockInfo.getPrice())
                        .eq(StockInfo::getId, stockInfo.getId()));
            } else {
                // 重新添加库房数据
                StockInfo stock = new StockInfo();
                stock.setName(item.getName());
                stock.setAmount(item.getAmount());
                stock.setCreateDate(DateUtil.formatDateTime(new Date()));
                stock.setType(item.getType());
                stock.setTypeId(item.getTypeId());
                stock.setUnit(item.getUnit());
                stock.setPrice(item.getPrice());
                stock.setIsIn(0);
                stockInfo = stock;
                this.save(stock);
            }
            // 添加入库记录
            StockInfo stockInfoPut = new StockInfo();
            stockInfoPut.setParentId(stockInfo.getId());
            stockInfoPut.setName(item.getName());
            stockInfoPut.setAmount(item.getAmount());
            stockInfoPut.setCreateDate(DateUtil.formatDateTime(new Date()));
            stockInfoPut.setType(item.getType());
            stockInfoPut.setTypeId(item.getTypeId());
            stockInfoPut.setUnit(item.getUnit());
            stockInfoPut.setPrice(item.getPrice());
            stockInfoPut.setIsIn(1);
            this.save(stockInfoPut);

            // 添加所属信息
            GoodsBelong goodsBelong = new GoodsBelong();
            goodsBelong.setNum(stockPut.getNum());
            goodsBelong.setCreateDate(DateUtil.formatDateTime(new Date()));
            goodsBelong.setAmount(item.getAmount());
            goodsBelong.setName(item.getName());
            goodsBelong.setPrice(item.getPrice());
            goodsBelong.setType(item.getType());
            goodsBelong.setTypeId(item.getTypeId());
            goodsBelong.setUnit(item.getUnit());
            goodsBelongService.save(goodsBelong);
        });
        return true;
    }

    public boolean checkStock(List<GoodsBelong> goodsBelongList) {
        // 库房数量
        List<StockInfo> stockList = stockInfoMapper.selectList(Wrappers.<StockInfo>lambdaQuery().eq(StockInfo::getIsIn, 0));
        Integer num = 0;
        if (CollectionUtil.isNotEmpty(stockList)) {
            for (StockInfo stockInfo : stockList) {
                if (stockInfo.getAmount() != null) {
                    num += stockInfo.getAmount();
                }
            }
        }
        Integer putNum = 0;
        for (GoodsBelong goodsBelong : goodsBelongList) {
            if (goodsBelong.getAmount() != null) {
                putNum += goodsBelong.getAmount();
            }
        }
        return (num + putNum) > 1000;
    }

    @Override
    public IPage<LinkedHashMap<String, Object>> stockInfoDetailPage(Page page, StockInfo stockInfo) {
        return baseMapper.stockInfoDetailPage(page, stockInfo);
    }

    @Override
    public IPage<LinkedHashMap<String, Object>> getGoodsPutByUserId(Page page, StockInfo stockInfo) {
        return baseMapper.getGoodsPutByUserId(page, stockInfo);
    }

    @Override
    public LinkedHashMap<String, Object> home(Integer type, Integer userId) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("bulletinList", bulletinInfoService.list());
        if (type == 75) {
            result.put("studentInfo", archivesInfoService.getOne(Wrappers.<ArchivesInfo>lambdaQuery().eq(ArchivesInfo::getUserId, userId)));
        }
        result.put("stockPutRate", baseMapper.stockPutRate());
        result.put("stockPutTypeRate", baseMapper.stockPutTypeRate());
        result.put("stockOutRate", baseMapper.stockOutRate());
        result.put("stockOutTypeRate", baseMapper.stockOutTypeRate());
        result.put("stockInfo", baseMapper.stockInfoByMonth());
        return result;
    }
}
