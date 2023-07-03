package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.RurchaseRequest;
import cc.mrbird.febs.cos.service.IRurchaseRequestService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/rurchase-request")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RurchaseRequestController {

    private final IRurchaseRequestService rurchaseRequestService;

    /**
     * 采购申请入库
     *
     * @param rurchaseRequest 采购申请
     * @return 结果
     */
    @PostMapping("/rurchasePut")
    public R rurchasePut(RurchaseRequest rurchaseRequest) {
        return R.ok(rurchaseRequestService.rurchasePut(rurchaseRequest));
    }

    /**
     * 分页获取采购申请
     *
     * @param page            分页对象
     * @param rurchaseRequest 采购申请
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<RurchaseRequest> page, RurchaseRequest rurchaseRequest) {
        return R.ok(rurchaseRequestService.rurchaseRequestByPage(page, rurchaseRequest));
    }

    /**
     * 添加采购申请
     *
     * @param rurchaseRequest 采购申请
     * @return 结果
     */
    @PostMapping
    public R save(RurchaseRequest rurchaseRequest) {
        rurchaseRequest.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(rurchaseRequestService.rurchaseRequestAdd(rurchaseRequest));
    }

    /**
     * 修改采购申请
     *
     * @param rurchaseRequest 采购申请
     * @return 结果
     */
    @PutMapping
    public R edit(RurchaseRequest rurchaseRequest) {
        return R.ok(rurchaseRequestService.updateById(rurchaseRequest));
    }

    /**
     * 删除采购申请
     *
     * @param ids IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(rurchaseRequestService.removeByIds(ids));
    }

}
