package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.cos.entity.SafetyTask;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author FanK
 */
public interface ISafetyTaskService extends IService<SafetyTask> {

    /**
     * 分页获取巡检任务信息
     *
     * @param page       分页对象
     * @param safetyTask 巡检任务信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectSafetyTaskPage(Page<SafetyTask> page, SafetyTask safetyTask);

    /**
     * 添加巡检任务
     *
     * @param safetyTask 巡检任务
     * @return 结果
     */
    boolean saveSafetyTask(SafetyTask safetyTask) throws FebsException;

    /**
     * 巡检任务详情
     *
     * @param id 任务ID
     * @return 结果
     */
    LinkedHashMap<String, Object> taskDetail(Integer id);
}
