package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.WorkInfo;
import cc.mrbird.febs.cos.dao.WorkInfoMapper;
import cc.mrbird.febs.cos.service.IWorkInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class WorkInfoServiceImpl extends ServiceImpl<WorkInfoMapper, WorkInfo> implements IWorkInfoService {

    /**
     * 分页获取项目工程信息
     *
     * @param page     分页对象
     * @param workInfo 项目工程信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectWorkPage(Page<WorkInfo> page, WorkInfo workInfo) {
        return baseMapper.selectWorkPage(page, workInfo);
    }
}
