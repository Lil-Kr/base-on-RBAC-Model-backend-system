package com.cy.sys.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author CY
 * @since 2020-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据字典id唯一主键
     */
    private Long surrogateId;

    /**
     * 数据字典名称
     */
    private String name;

    /**
     * 数据字典类型, 从0开始递增
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除状态, 0正常, 1删除
     */
    private Integer deleted;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作ip
     */
    private String operateIp;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更改时间
     */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
