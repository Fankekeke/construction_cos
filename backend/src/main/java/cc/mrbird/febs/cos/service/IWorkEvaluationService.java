package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.WorkEvaluation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IWorkEvaluationService extends IService<WorkEvaluation> {

    /**
     * 分页获取用工评价信息
     *
     * @param page           分页对象
     * @param workEvaluation 用工评价信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectWorkEvaluatePage(Page<WorkEvaluation> page, WorkEvaluation workEvaluation);

    /**
     * 添加员工评价
     *
     * @param workEvaluation 评价信息
     * @return 结果
     * @throws Exception 异常
     */
    boolean evaluateAdd(WorkEvaluation workEvaluation) throws Exception;
}
