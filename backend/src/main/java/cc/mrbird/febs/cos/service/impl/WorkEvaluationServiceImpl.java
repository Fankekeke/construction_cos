package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.WorkEvaluation;
import cc.mrbird.febs.cos.dao.WorkEvaluationMapper;
import cc.mrbird.febs.cos.service.IWorkEvaluationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class WorkEvaluationServiceImpl extends ServiceImpl<WorkEvaluationMapper, WorkEvaluation> implements IWorkEvaluationService {

    /**
     * 分页获取用工评价信息
     *
     * @param page           分页对象
     * @param workEvaluation 用工评价信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectWorkEvaluatePage(Page<WorkEvaluation> page, WorkEvaluation workEvaluation) {
        return baseMapper.selectWorkEvaluatePage(page, workEvaluation);
    }
}
