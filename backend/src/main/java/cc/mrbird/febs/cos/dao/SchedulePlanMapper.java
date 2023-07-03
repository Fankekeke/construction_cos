package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.SchedulePlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface SchedulePlanMapper extends BaseMapper<SchedulePlan> {

    /**
     * 分页获取进度计划信息
     *
     * @param page         分页对象
     * @param schedulePlan 进度计划信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectSchedulePlanPage(Page<SchedulePlan> page, @Param("schedulePlan") SchedulePlan schedulePlan);
}
