package com.cy.sys.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CY
 * @since 2020-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 唯一主键
     */
    private Long surrogateId;

    /**
     * 员工编号
     */
    private String number;

    /**
     * 员工姓名
     */
    private String userName;

    /**
     * 员工电话
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户所在部门id
     */
    private Long deptId;

    /**
     * 状态, 0正常, 1冻结, 2: 删除
     */
    private Integer status;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作ip
     */
    private String operateIp;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更改时间
     */
    private String updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
