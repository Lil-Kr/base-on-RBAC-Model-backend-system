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
public class SysDeptTest extends Model<SysDeptTest> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门id
     */
    private Integer parentId;

    /**
     * 部门层级
     */
    private String level;

    /**
     * 部门在当前层级下的顺序，由小到大
     */
    private Integer seq;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 最后一次操作时间
     */
    private Date operateTime;

    /**
     * 最后一次更新操作者的ip地址
     */
    private String operateIp;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
