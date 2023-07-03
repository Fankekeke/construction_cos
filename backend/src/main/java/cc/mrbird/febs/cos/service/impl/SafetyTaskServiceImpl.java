package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.SafetyTask;
import cc.mrbird.febs.cos.dao.SafetyTaskMapper;
import cc.mrbird.febs.cos.service.ISafetyTaskService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class SafetyTaskServiceImpl extends ServiceImpl<SafetyTaskMapper, SafetyTask> implements ISafetyTaskService {

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
}
