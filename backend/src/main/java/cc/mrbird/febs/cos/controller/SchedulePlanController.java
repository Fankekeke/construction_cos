package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.SchedulePlan;
import cc.mrbird.febs.cos.service.ISchedulePlanService;
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
@RequestMapping("/cos/schedule-plan")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulePlanController {

    private final ISchedulePlanService schedulePlanService;

    /**
     * 分页获取进度计划信息
     *
     * @param page         分页对象
     * @param schedulePlan 进度计划信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<SchedulePlan> page, SchedulePlan schedulePlan) {
        return R.ok(schedulePlanService.selectSchedulePlanPage(page, schedulePlan));
    }

    /**
     * 获取进度计划信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(schedulePlanService.list());
    }

    /**
     * 获取进度计划详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(schedulePlanService.getById(id));
    }

    /**
     * 新增进度计划信息
     *
     * @param schedulePlan 进度计划信息
     * @return 结果
     */
    @PostMapping
    public R save(SchedulePlan schedulePlan) {
        return R.ok(schedulePlanService.save(schedulePlan));
    }

    /**
     * 修改进度计划信息
     *
     * @param schedulePlan 进度计划信息
     * @return 结果
     */
    @PutMapping
    public R edit(SchedulePlan schedulePlan) {
        return R.ok(schedulePlanService.updateById(schedulePlan));
    }

    /**
     * 删除进度计划信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(schedulePlanService.removeByIds(ids));
    }
}
