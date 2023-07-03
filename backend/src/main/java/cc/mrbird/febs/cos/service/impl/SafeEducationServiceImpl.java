package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.SafeEducation;
import cc.mrbird.febs.cos.dao.SafeEducationMapper;
import cc.mrbird.febs.cos.service.ISafeEducationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class SafeEducationServiceImpl extends ServiceImpl<SafeEducationMapper, SafeEducation> implements ISafeEducationService {

    /**
     * 分页获取安全教育信息
     *
     * @param page         分页对象
     * @param safeEducation 安全教育信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectSafeEducationPage(Page<SafeEducation> page, SafeEducation safeEducation) {
        return baseMapper.selectSafeEducationPage(page, safeEducation);
    }
}
