package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.StationInfo;
import cc.mrbird.febs.cos.service.IStationInfoService;
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
@RequestMapping("/cos/station-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StationInfoController {

    private final IStationInfoService stationInfoService;

    /**
     * 分页获取巡检地址信息
     *
     * @param page        分页对象
     * @param stationInfo 巡检地址信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<StationInfo> page, StationInfo stationInfo) {
        return R.ok(stationInfoService.selectStationPage(page, stationInfo));
    }

    /**
     * 获取巡检地址信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(stationInfoService.list());
    }

    /**
     * 获取巡检地址详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(stationInfoService.getById(id));
    }

    /**
     * 新增巡检地址信息
     *
     * @param stationInfo 巡检地址信息
     * @return 结果
     */
    @PostMapping
    public R save(StationInfo stationInfo) {
        stationInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(stationInfoService.save(stationInfo));
    }

    /**
     * 修改巡检地址信息
     *
     * @param stationInfo 巡检地址信息
     * @return 结果
     */
    @PutMapping
    public R edit(StationInfo stationInfo) {
        return R.ok(stationInfoService.updateById(stationInfo));
    }

    /**
     * 删除巡检地址信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(stationInfoService.removeByIds(ids));
    }
}
