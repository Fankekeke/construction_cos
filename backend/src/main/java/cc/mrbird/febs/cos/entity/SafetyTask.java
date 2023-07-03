package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 巡检任务
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SafetyTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工编号
     */
    private String staffCode;

    /**
     * 巡站点
     */
    private String stationIds;

    /**
     * 巡检站点名称
     */
    private String stationNames;

    /**
     * 创建时间
     */
    private String createDate;

    @TableField(exist = false)
    private String staffName;
}
