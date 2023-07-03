package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ArchivesInfo;
import cc.mrbird.febs.cos.dao.ArchivesInfoMapper;
import cc.mrbird.febs.cos.service.IArchivesInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class ArchivesInfoServiceImpl extends ServiceImpl<ArchivesInfoMapper, ArchivesInfo> implements IArchivesInfoService {

    /**
     * 分页获取档案信息
     *
     * @param page         分页对象
     * @param archivesInfo 档案信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectArchivesPage(Page<ArchivesInfo> page, ArchivesInfo archivesInfo) {
        return baseMapper.selectArchivesPage(page, archivesInfo);
    }
}
