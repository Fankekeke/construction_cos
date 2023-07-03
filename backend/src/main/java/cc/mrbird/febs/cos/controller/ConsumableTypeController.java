package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ConsumableType;
import cc.mrbird.febs.cos.service.IConsumableTypeService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/consumable-type")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConsumableTypeController {

    private final IConsumableTypeService consumableTypeService;

    /**
     * 获取所有耗材类别
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(consumableTypeService.list());
    }

    /**
     * 分页查询耗材类型信息
     *
     * @param page           分页对象
     * @param consumableType 耗材类型
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ConsumableType> page, ConsumableType consumableType) {
        return R.ok(consumableTypeService.page(page, Wrappers.<ConsumableType>lambdaQuery()
                .like(!StrUtil.isBlank(consumableType.getName()), ConsumableType::getName, consumableType.getName())
                .like(!StrUtil.isBlank(consumableType.getContent()), ConsumableType::getContent, consumableType.getContent())));
    }

    /**
     * 添加耗材类型信息
     *
     * @param consumableType 耗材类型
     * @return 结果
     */
    @PostMapping
    public R save(ConsumableType consumableType) {
        consumableType.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(consumableTypeService.save(consumableType));
    }

    /**
     * 修改耗材类型信息
     *
     * @param consumableType 耗材类型
     * @return 结果
     */
    @PutMapping
    public R edit(ConsumableType consumableType) {
        return R.ok(consumableTypeService.updateById(consumableType));
    }

    /**
     * 删除耗材类型信息
     *
     * @param ids IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(consumableTypeService.removeByIds(ids));
    }


}
