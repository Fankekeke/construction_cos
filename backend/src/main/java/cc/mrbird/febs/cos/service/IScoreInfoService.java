package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ScoreInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IScoreInfoService extends IService<ScoreInfo> {

    /**
     * 分页获取安全评分信息
     *
     * @param page      分页对象
     * @param scoreInfo 安全评分信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryScorePage(Page<ScoreInfo> page, ScoreInfo scoreInfo);
}
