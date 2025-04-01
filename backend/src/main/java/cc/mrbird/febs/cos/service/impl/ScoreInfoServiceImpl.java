package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ScoreInfo;
import cc.mrbird.febs.cos.dao.ScoreInfoMapper;
import cc.mrbird.febs.cos.service.IScoreInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class ScoreInfoServiceImpl extends ServiceImpl<ScoreInfoMapper, ScoreInfo> implements IScoreInfoService {

    /**
     * 分页获取安全评分信息
     *
     * @param page      分页对象
     * @param scoreInfo 安全评分信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryScorePage(Page<ScoreInfo> page, ScoreInfo scoreInfo) {
        return baseMapper.queryScorePage(page, scoreInfo);
    }
}
