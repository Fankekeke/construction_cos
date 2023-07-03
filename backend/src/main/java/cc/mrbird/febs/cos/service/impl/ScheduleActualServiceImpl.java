package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ScheduleActual;
import cc.mrbird.febs.cos.dao.ScheduleActualMapper;
import cc.mrbird.febs.cos.service.IScheduleActualService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class ScheduleActualServiceImpl extends ServiceImpl<ScheduleActualMapper, ScheduleActual> implements IScheduleActualService {

    /**
     * 分页获取阶段实际完成信息
     *
     * @param page       分页对象
     * @param scheduleActual 阶段实际完成信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectScheduleActualPage(Page<ScheduleActual> page, ScheduleActual scheduleActual) {
        return baseMapper.selectScheduleActualPage(page, scheduleActual);
    }
}
