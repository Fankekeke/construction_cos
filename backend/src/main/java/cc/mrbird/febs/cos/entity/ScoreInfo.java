package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 安全评分
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScoreInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工编号
     */
    private String staffCode;

    /**
     * 工作得分
     */
    private BigDecimal workScore;

    /**
     * 评价人
     */
    private String appraiser;

    /**
     * 备注
     */
    private String remark;

    /**
     * 评价时间
     */
    private String createDate;

    /**
     * 所属安全教育
     */
    private Integer safeId;

    @TableField(exist = false)
    private String staffName;

    @TableField(exist = false)
    private String title;


}
