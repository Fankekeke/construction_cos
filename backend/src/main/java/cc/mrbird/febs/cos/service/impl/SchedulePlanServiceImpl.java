package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.SchedulePlan;
import cc.mrbird.febs.cos.dao.SchedulePlanMapper;
import cc.mrbird.febs.cos.service.ISchedulePlanService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class SchedulePlanServiceImpl extends ServiceImpl<SchedulePlanMapper, SchedulePlan> implements ISchedulePlanService {

    /**
     * 分页获取进度计划信息
     *
     * @param page         分页对象
     * @param schedulePlan 进度计划信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectSchedulePlanPage(Page<SchedulePlan> page, SchedulePlan schedulePlan) {
        return baseMapper.selectSchedulePlanPage(page, schedulePlan);
    }
}
