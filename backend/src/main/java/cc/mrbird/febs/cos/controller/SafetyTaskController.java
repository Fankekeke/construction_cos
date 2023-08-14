package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.SafetyTask;
import cc.mrbird.febs.cos.service.ISafetyTaskService;
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
@RequestMapping("/cos/safety-task")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SafetyTaskController {

    private final ISafetyTaskService safetyTaskService;

    /**
     * 分页获取巡检任务信息
     *
     * @param page       分页对象
     * @param safetyTask 巡检任务信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<SafetyTask> page, SafetyTask safetyTask) {
        return R.ok(safetyTaskService.selectSafetyTaskPage(page, safetyTask));
    }

    /**
     * 获取巡检任务信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(safetyTaskService.list());
    }

    /**
     * 获取巡检任务详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(safetyTaskService.taskDetail(id));
    }

    /**
     * 新增巡检任务信息
     *
     * @param safetyTask 巡检任务信息
     * @return 结果
     */
    @PostMapping
    public R save(SafetyTask safetyTask) throws FebsException {
        return R.ok(safetyTaskService.saveSafetyTask(safetyTask));
    }

    /**
     * 修改巡检任务信息
     *
     * @param safetyTask 巡检任务信息
     * @return 结果
     */
    @PutMapping
    public R edit(SafetyTask safetyTask) {
        return R.ok(safetyTaskService.updateById(safetyTask));
    }

    /**
     * 删除巡检任务信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(safetyTaskService.removeByIds(ids));
    }

}
