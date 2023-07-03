package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.WorkEvaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface WorkEvaluationMapper extends BaseMapper<WorkEvaluation> {

    /**
     * 分页获取用工评价信息
     *
     * @param page           分页对象
     * @param workEvaluation 用工评价信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectWorkEvaluatePage(Page<WorkEvaluation> page, @Param("workEvaluation") WorkEvaluation workEvaluation);
}
