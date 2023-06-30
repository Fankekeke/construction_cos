package cc.mrbird.febs.cos.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 安全教育
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SafeEducation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 主题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 图册
     */
    private String images;

    /**
     * 所属会议室
     */
    private String meetRoom;

    /**
     * 主持人
     */
    private String hostName;

    /**
     * 时间截至日期
     */
    private String timeOrder;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 删除标识
     */
    private String delFlag;


}
