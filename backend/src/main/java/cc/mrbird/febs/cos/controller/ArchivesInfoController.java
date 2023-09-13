package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ArchivesInfo;
import cc.mrbird.febs.cos.service.IArchivesInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
@RequestMapping("/cos/archives-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArchivesInfoController {

    private final IArchivesInfoService archivesInfoService;

    /**
     * 分页获取档案信息
     *
     * @param page         分页对象
     * @param archivesInfo 档案信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ArchivesInfo> page, ArchivesInfo archivesInfo) {
        return R.ok(archivesInfoService.selectArchivesPage(page, archivesInfo));
    }

    /**
     * 获取档案信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(archivesInfoService.list(Wrappers.<ArchivesInfo>lambdaQuery().eq(ArchivesInfo::getDelFlag, "0")));
    }

    /**
     * 获取档案详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(archivesInfoService.getById(id));
    }

    /**
     * 新增档案信息
     *
     * @param archivesInfo 档案信息
     * @return 结果
     */
    @PostMapping
    public R save(ArchivesInfo archivesInfo) {
        archivesInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        archivesInfo.setCode("AR-" + System.currentTimeMillis());
        return R.ok(archivesInfoService.save(archivesInfo));
    }

    /**
     * 修改档案信息
     *
     * @param archivesInfo 档案信息
     * @return 结果
     */
    @PutMapping
    public R edit(ArchivesInfo archivesInfo) {
        return R.ok(archivesInfoService.updateById(archivesInfo));
    }

    /**
     * 删除档案信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(archivesInfoService.removeByIds(ids));
    }
}
