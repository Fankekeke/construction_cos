package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.SafeEducation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface ISafeEducationService extends IService<SafeEducation> {

    /**
     * 分页获取安全教育信息
     *
     * @param page         分页对象
     * @param safeEducation 安全教育信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectSafeEducationPage(Page<SafeEducation> page, SafeEducation safeEducation);
}
