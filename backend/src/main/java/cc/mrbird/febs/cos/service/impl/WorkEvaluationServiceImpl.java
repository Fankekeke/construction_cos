package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.cos.entity.WorkEvaluation;
import cc.mrbird.febs.cos.dao.WorkEvaluationMapper;
import cc.mrbird.febs.cos.service.IWorkEvaluationService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

    /**
     * 添加员工评价
     *
     * @param workEvaluation 评价信息
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    public boolean evaluateAdd(WorkEvaluation workEvaluation) throws Exception {
        if (StrUtil.isEmpty(workEvaluation.getStaffCode())) {
            throw new FebsException("请选择员工");
        }

        // 判断是否已经评价
        int count = this.count(Wrappers.<WorkEvaluation>lambdaQuery().eq(WorkEvaluation::getStaffCode, workEvaluation.getStaffCode())
                .eq(WorkEvaluation::getYear, workEvaluation.getYear()).eq(WorkEvaluation::getMonth, workEvaluation.getMonth()).eq(WorkEvaluation::getDelFlag, "0"));
        if (count > 0) {
            throw new FebsException("此月份已添加评价信息");
        }
        return this.save(workEvaluation);
    }

    /**
     * 修改员工评价
     *
     * @param workEvaluation 评价信息
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    public boolean evaluateEdit(WorkEvaluation workEvaluation) throws Exception {
        if (StrUtil.isEmpty(workEvaluation.getStaffCode())) {
            throw new FebsException("请选择员工");
        }
        // 判断是否已经评价
        WorkEvaluation evaluation = this.getOne(Wrappers.<WorkEvaluation>lambdaQuery().eq(WorkEvaluation::getStaffCode, workEvaluation.getStaffCode())
                .eq(WorkEvaluation::getYear, workEvaluation.getYear()).eq(WorkEvaluation::getMonth, workEvaluation.getMonth()).eq(WorkEvaluation::getDelFlag, "0"));
        if (evaluation != null && !evaluation.getId().equals(workEvaluation.getId())) {
            throw new FebsException("此月份已添加评价信息");
        }
        return this.updateById(workEvaluation);
    }
}
