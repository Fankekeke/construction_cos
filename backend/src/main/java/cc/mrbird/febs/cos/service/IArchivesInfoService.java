package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ArchivesInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IArchivesInfoService extends IService<ArchivesInfo> {

    /**
     * 分页获取档案信息
     *
     * @param page         分页对象
     * @param archivesInfo 档案信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectArchivesPage(Page<ArchivesInfo> page, ArchivesInfo archivesInfo);
}
