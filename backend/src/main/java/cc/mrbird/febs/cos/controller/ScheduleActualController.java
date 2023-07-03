package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ScheduleActual;
import cc.mrbird.febs.cos.service.IScheduleActualService;
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
@RequestMapping("/cos/schedule-actual")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleActualController {

    private final IScheduleActualService scheduleActualService;

    /**
     * 分页获取阶段实际完成信息
     *
     * @param page       分页对象
     * @param scheduleActual 阶段实际完成信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ScheduleActual> page, ScheduleActual scheduleActual) {
        return R.ok(scheduleActualService.selectScheduleActualPage(page, scheduleActual));
    }

    /**
     * 获取阶段实际完成信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(scheduleActualService.list());
    }

    /**
     * 获取阶段实际完成详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(scheduleActualService.getById(id));
    }

    /**
     * 新增阶段实际完成信息
     *
     * @param scheduleActual 阶段实际完成信息
     * @return 结果
     */
    @PostMapping
    public R save(ScheduleActual scheduleActual) {
        return R.ok(scheduleActualService.save(scheduleActual));
    }

    /**
     * 修改阶段实际完成信息
     *
     * @param scheduleActual 阶段实际完成信息
     * @return 结果
     */
    @PutMapping
    public R edit(ScheduleActual scheduleActual) {
        return R.ok(scheduleActualService.updateById(scheduleActual));
    }

    /**
     * 删除阶段实际完成信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(scheduleActualService.removeByIds(ids));
    }
}
