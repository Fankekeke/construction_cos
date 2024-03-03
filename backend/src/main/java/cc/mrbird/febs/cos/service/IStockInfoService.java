package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.cos.entity.GoodsBelong;
import cc.mrbird.febs.cos.entity.StockInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface IStockInfoService extends IService<StockInfo> {

    // 分页获取库房信息
    IPage<LinkedHashMap<String, Object>> stockInfoByPage(Page page, StockInfo stockInfo);

    // 入库
    Boolean stockPut(String goods, String custodian, String putUser, String content, BigDecimal price) throws FebsException;

    // 分页获取物品出入库详情
    IPage<LinkedHashMap<String, Object>> stockInfoDetailPage(Page page, StockInfo stockInfo);

    // 根号用户ID获取领取耗材
    IPage<LinkedHashMap<String, Object>> getGoodsPutByUserId(Page page,StockInfo stockInfo);

    // 获取主页信息
    LinkedHashMap<String, Object> home(Integer type, Integer userId);

    boolean checkStock(List<GoodsBelong> goodsBelongList);

    /**
     * 根据月份获取药品统计情况
     *
     * @param date 日期
     * @return 结果
     */
    LinkedHashMap<String, Object> selectStatisticsByMonth(String date) throws FebsException;
}
