package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.WorkInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface WorkInfoMapper extends BaseMapper<WorkInfo> {

    /**
     * 分页获取项目工程信息
     *
     * @param page     分页对象
     * @param workInfo 项目工程信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectWorkPage(Page<WorkInfo> page, @Param("workInfo") WorkInfo workInfo);
}
