package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.SafeEducation;
import cc.mrbird.febs.cos.service.ISafeEducationService;
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
@RequestMapping("/cos/safe-education")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SafeEducationController {

    private final ISafeEducationService safeEducationService;

    /**
     * 分页获取安全教育信息
     *
     * @param page          分页对象
     * @param safeEducation 安全教育信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<SafeEducation> page, SafeEducation safeEducation) {
        return R.ok(safeEducationService.selectSafeEducationPage(page, safeEducation));
    }

    /**
     * 获取安全教育信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(safeEducationService.list());
    }

    /**
     * 获取安全教育详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(safeEducationService.getById(id));
    }

    /**
     * 新增安全教育信息
     *
     * @param safeEducation 安全教育信息
     * @return 结果
     */
    @PostMapping
    public R save(SafeEducation safeEducation) {
        safeEducation.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(safeEducationService.save(safeEducation));
    }

    /**
     * 修改安全教育信息
     *
     * @param safeEducation 安全教育信息
     * @return 结果
     */
    @PutMapping
    public R edit(SafeEducation safeEducation) {
        return R.ok(safeEducationService.updateById(safeEducation));
    }

    /**
     * 删除安全教育信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(safeEducationService.removeByIds(ids));
    }
}
