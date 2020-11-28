package com.cy.sys.pojo.param.user;

import com.cy.common.utils.checkUtil.CheckUtil;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString
public class UserSaveParam {

    private Long id;

    private Long surrogateId;

    /**
     * 用户注册账号
     */
    @NotNull(message = "用户账号不能为空")
    @Length(max = 50, message = "用户账号长度必须在50个字符以内")
    private String loginAccount;

    /**
     * 员工姓名
     */
    @NotNull(message = "用户名不能为空")
    @Length(min = 2,max = 20, message = "用户名长度必须在2到20个字符之间")
    private String userName;

    /**
     * 员工电话
     */
    @NotNull(message = "用户名手机号不能为空")
    @Length(max = 13, message = "用户号码长度不能超过13个字符")
    private String telephone;

    /**
     * 邮箱
     */
    @NotNull(message = "用户邮箱不能为空")
    @Pattern(regexp = CheckUtil.EMAIL_REGEXP,message = "邮箱格式不正确")
    private String mail;

    /**
     * 用户所在部门id
     */
    @NotNull(message = "用户所在部门不能为空")
    private Long deptId;

    /**
     * 状态, 0正常, 1冻结
     */
    @Min(value = 1,message = "状态不合法")
    @Max(value = 2,message = "状态不合法")
    private Integer status = 0;

    /**
     * 删除状态 0正常, 1删除
     */
    private Integer deleted = 0;

    /**
     * 备注
     */
    @Length(max = 100, message = "备注不能超过100个字符")
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更改时间
     */
    private String updateTime;
}