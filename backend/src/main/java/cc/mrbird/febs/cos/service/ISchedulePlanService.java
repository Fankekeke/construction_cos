package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.SchedulePlan;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface ISchedulePlanService extends IService<SchedulePlan> {

    /**
     * 分页获取进度计划信息
     *
     * @param page         分页对象
     * @param schedulePlan 进度计划信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectSchedulePlanPage(Page<SchedulePlan> page, SchedulePlan schedulePlan);
}
