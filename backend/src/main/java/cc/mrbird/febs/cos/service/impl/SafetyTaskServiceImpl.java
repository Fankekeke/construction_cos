package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.cos.dao.ArchivesInfoMapper;
import cc.mrbird.febs.cos.dao.StationInfoMapper;
import cc.mrbird.febs.cos.entity.ArchivesInfo;
import cc.mrbird.febs.cos.entity.SafetyTask;
import cc.mrbird.febs.cos.dao.SafetyTaskMapper;
import cc.mrbird.febs.cos.entity.StationInfo;
import cc.mrbird.febs.cos.service.ISafetyTaskService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SafetyTaskServiceImpl extends ServiceImpl<SafetyTaskMapper, SafetyTask> implements ISafetyTaskService {

    private final StationInfoMapper stationInfoMapper;

    private final ArchivesInfoMapper archivesInfoMapper;

    /**
     * 分页获取巡检任务信息
     *
     * @param page       分页对象
     * @param safetyTask 巡检任务信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectSafetyTaskPage(Page<SafetyTask> page, SafetyTask safetyTask) {
        return baseMapper.selectSafetyTaskPage(page, safetyTask);
    }

    /**
     * 添加巡检任务
     *
     * @param safetyTask 巡检任务
     * @return 结果
     */
    @Override
    public boolean saveSafetyTask(SafetyTask safetyTask) throws FebsException {
        // 判断当前员工是否有其他巡检任务
        boolean staffTask = this.count(Wrappers.<SafetyTask>lambdaQuery().eq(SafetyTask::getStaffCode, safetyTask.getStaffCode())) == 0;

        if (!staffTask) {
            throw new FebsException("已经存在巡检任务！");
        }
        safetyTask.setCreateDate(DateUtil.formatDate(new Date()));

        // 获取站点信息
        List<String> stationIds = Arrays.asList(StrUtil.split(safetyTask.getStationIds(), ","));
        List<StationInfo> stationList = stationInfoMapper.selectList(Wrappers.<StationInfo>lambdaQuery().in(StationInfo::getId,stationIds));

        // 保存站点名称
        List<String> stationName = stationList.stream().map(StationInfo::getStationName).collect(Collectors.toList());
        safetyTask.setStationNames(StrUtil.join(",", stationName));
        return this.save(safetyTask);
    }

    /**
     * 巡检任务详情
     *
     * @param id 任务ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> taskDetail(Integer id) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        // 获取任务信息
        SafetyTask task = this.getById(id);
        result.put("task", task);
        List<String> stationIds = Arrays.asList(StrUtil.split(task.getStationIds(), ","));
        // 站点信息
        List<StationInfo> stationList = stationInfoMapper.selectBatchIds(stationIds);
        result.put("stationList", stationList);
        // 员工信息
        ArchivesInfo staff = archivesInfoMapper.selectOne(Wrappers.<ArchivesInfo>lambdaQuery().eq(ArchivesInfo::getCode, task.getStaffCode()));
        result.put("staff", staff);
        return result;
    }
}
