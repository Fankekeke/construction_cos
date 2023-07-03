package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ArchivesInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface ArchivesInfoMapper extends BaseMapper<ArchivesInfo> {

    /**
     * 分页获取档案信息
     *
     * @param page         分页对象
     * @param archivesInfo 档案信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectArchivesPage(Page<ArchivesInfo> page, @Param("archivesInfo") ArchivesInfo archivesInfo);
}
