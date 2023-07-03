package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.WorkEvaluation;
import cc.mrbird.febs.cos.service.IWorkEvaluationService;
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
@RequestMapping("/cos/work-evaluation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkEvaluationController {

    private final IWorkEvaluationService workEvaluationService;

    /**
     * 分页获取用工评价信息
     *
     * @param page           分页对象
     * @param workEvaluation 用工评价信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<WorkEvaluation> page, WorkEvaluation workEvaluation) {
        return R.ok(workEvaluationService.selectWorkEvaluatePage(page, workEvaluation));
    }

    /**
     * 获取用工评价信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(workEvaluationService.list());
    }

    /**
     * 获取用工评价详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(workEvaluationService.getById(id));
    }

    /**
     * 新增用工评价信息
     *
     * @param workEvaluation 用工评价信息
     * @return 结果
     */
    @PostMapping
    public R save(WorkEvaluation workEvaluation) {
        workEvaluation.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(workEvaluationService.save(workEvaluation));
    }

    /**
     * 修改用工评价信息
     *
     * @param workEvaluation 用工评价信息
     * @return 结果
     */
    @PutMapping
    public R edit(WorkEvaluation workEvaluation) {
        return R.ok(workEvaluationService.updateById(workEvaluation));
    }

    /**
     * 删除用工评价信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(workEvaluationService.removeByIds(ids));
    }
}
