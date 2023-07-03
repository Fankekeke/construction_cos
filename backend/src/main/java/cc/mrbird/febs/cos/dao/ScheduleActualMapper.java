package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ScheduleActual;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface ScheduleActualMapper extends BaseMapper<ScheduleActual> {

    /**
     * 分页获取阶段实际完成信息
     *
     * @param page       分页对象
     * @param scheduleActual 阶段实际完成信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectScheduleActualPage(Page<ScheduleActual> page, @Param("scheduleActual") ScheduleActual scheduleActual);
}
