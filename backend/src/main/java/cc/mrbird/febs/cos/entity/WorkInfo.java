package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 项目工程
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WorkInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目编号
     */
    private String code;

    /**
     * 项目名称
     */
    private String workName;

    /**
     * 项目内容
     */
    private String content;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 工作员工
     */
    private String workStaff;

    /**
     * 项目状态（0.未开始 1.已开始 2.已结束 3.已验收）
     */
    private String status;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 删除标识
     */
    private String delFlag;


}
