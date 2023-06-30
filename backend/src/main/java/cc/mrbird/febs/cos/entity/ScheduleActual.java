package cc.mrbird.febs.cos.entity;

import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 阶段实际完成
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScheduleActual implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目编号
     */
    private String workCode;

    /**
     * 排序
     */
    private Integer sortNo;

    /**
     * 阶段名称
     */
    private String stageName;

    /**
     * 实际完成日期
     */
    private String checkDate;

    /**
     * 阶段备注
     */
    private String remark;

    /**
     * 验收图片
     */
    private String images;

    /**
     * 填写人
     */
    private String createBy;


}
