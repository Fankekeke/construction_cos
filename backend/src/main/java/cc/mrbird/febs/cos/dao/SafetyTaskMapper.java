package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.SafetyTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface SafetyTaskMapper extends BaseMapper<SafetyTask> {

    /**
     * 分页获取巡检任务信息
     *
     * @param page       分页对象
     * @param safetyTask 巡检任务信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectSafetyTaskPage(Page<SafetyTask> page, @Param("safetyTask") SafetyTask safetyTask);
}
