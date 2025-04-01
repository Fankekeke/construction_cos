package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ScoreInfo;
import cc.mrbird.febs.cos.service.IScoreInfoService;
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
@RequestMapping("/cos/score-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScoreInfoController {

    private final IScoreInfoService scoreInfoService;

    /**
     * 分页获取安全评分信息
     *
     * @param page      分页对象
     * @param scoreInfo 安全评分信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ScoreInfo> page, ScoreInfo scoreInfo) {
        return R.ok(scoreInfoService.queryScorePage(page, scoreInfo));
    }

    /**
     * 获取安全评分信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(scoreInfoService.list());
    }

    /**
     * 获取安全评分详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(scoreInfoService.getById(id));
    }

    /**
     * 新增安全评分信息
     *
     * @param scoreInfo 安全评分信息
     * @return 结果
     */
    @PostMapping
    public R save(ScoreInfo scoreInfo) {
        scoreInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(scoreInfoService.save(scoreInfo));
    }

    /**
     * 修改安全评分信息
     *
     * @param scoreInfo 安全评分信息
     * @return 结果
     */
    @PutMapping
    public R edit(ScoreInfo scoreInfo) {
        return R.ok(scoreInfoService.updateById(scoreInfo));
    }

    /**
     * 删除安全评分信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(scoreInfoService.removeByIds(ids));
    }
}
