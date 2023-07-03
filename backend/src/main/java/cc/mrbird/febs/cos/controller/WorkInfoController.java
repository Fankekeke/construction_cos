package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.WorkInfo;
import cc.mrbird.febs.cos.service.IWorkInfoService;
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
@RequestMapping("/cos/work-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkInfoController {

    private final IWorkInfoService workInfoService;

    /**
     * 分页获取项目工程信息
     *
     * @param page     分页对象
     * @param workInfo 项目工程信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<WorkInfo> page, WorkInfo workInfo) {
        return R.ok(workInfoService.selectWorkPage(page, workInfo));
    }

    /**
     * 获取项目工程信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(workInfoService.list());
    }

    /**
     * 获取项目工程详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(workInfoService.getById(id));
    }

    /**
     * 新增项目工程信息
     *
     * @param workInfo 项目工程信息
     * @return 结果
     */
    @PostMapping
    public R save(WorkInfo workInfo) {
        workInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(workInfoService.save(workInfo));
    }

    /**
     * 修改项目工程信息
     *
     * @param workInfo 项目工程信息
     * @return 结果
     */
    @PutMapping
    public R edit(WorkInfo workInfo) {
        return R.ok(workInfoService.updateById(workInfo));
    }

    /**
     * 删除项目工程信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(workInfoService.removeByIds(ids));
    }
}
