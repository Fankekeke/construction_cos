package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.StockInfo;
import cc.mrbird.febs.cos.service.IStockInfoService;
import cc.mrbird.febs.cos.service.IStockPutService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/stock-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockInfoController {

    private final IStockInfoService stockInfoService;

    private final IStockPutService stockPutService;

    /**
     * 获取主页信息
     *
     * @param type 类型
     * @return 结果
     */
    @GetMapping("/home")
    public R home(Integer type, Integer userId) {
        return R.ok(stockInfoService.home(type, userId));
    }

    /**
     * 根号用户ID获取领取耗材
     *
     * @param stockInfo 库存
     * @return 结果
     */
    @GetMapping("/getGoodsPutByUserId")
    public R getGoodsPutByUserId(Page<StockInfo> page, StockInfo stockInfo) {
        return R.ok(stockInfoService.getGoodsPutByUserId(page, stockInfo));
    }

    /**
     * 入库
     *
     * @param goods
     * @param custodian
     * @param putUser
     * @param content
     * @return 结果
     */
    @PostMapping("/put")
    public R put(String goods, String custodian, String putUser, String content, BigDecimal price) throws FebsException {
        return R.ok(stockInfoService.stockPut(goods, custodian, putUser, content, price));
    }

    /**
     * 分页获取物品出入库详情
     *
     * @param page
     * @param stockInfo
     * @return
     */
    @GetMapping("/detail/page")
    public R stockInfoDetailPage(Page page, StockInfo stockInfo) {
        return R.ok(stockInfoService.stockInfoDetailPage(page, stockInfo));
    }

    /**
     * 分页获取库房信息
     *
     * @param page
     * @param stockInfo
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, StockInfo stockInfo) {
        return R.ok(stockInfoService.stockInfoByPage(page, stockInfo));
    }

    /**
     * 添加库房信息
     *
     * @param stockInfo
     * @return
     */
    @PostMapping
    public R save(StockInfo stockInfo) {
        return R.ok();
    }

    /**
     * 根据月份获取药品统计情况
     *
     * @param date 日期
     * @return 结果
     */
    @GetMapping("/selectStatisticsByMonth")
    public R selectStatisticsByMonth(@RequestParam("date") String date) throws FebsException {
        return R.ok(stockInfoService.selectStatisticsByMonth(date));
    }


}
