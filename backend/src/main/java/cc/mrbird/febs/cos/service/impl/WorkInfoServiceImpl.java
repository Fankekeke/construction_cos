package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.WorkInfo;
import cc.mrbird.febs.cos.dao.WorkInfoMapper;
import cc.mrbird.febs.cos.service.IWorkInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author FanK
 */
@Service
public class WorkInfoServiceImpl extends ServiceImpl<WorkInfoMapper, WorkInfo> implements IWorkInfoService {

}