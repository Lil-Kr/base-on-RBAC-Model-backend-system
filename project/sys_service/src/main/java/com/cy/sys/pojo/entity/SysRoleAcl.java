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
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleAcl extends Model<SysRoleAcl> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色-权限id唯一主键
     */
    private Long surrogateKey;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long aclId;

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
