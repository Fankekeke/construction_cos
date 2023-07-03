package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.StationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface StationInfoMapper extends BaseMapper<StationInfo> {

    /**
     * 分页获取巡检地址信息
     *
     * @param page        分页对象
     * @param stationInfo 巡检地址信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectStationPage(Page<StationInfo> page, @Param("stationInfo") StationInfo stationInfo);
}
