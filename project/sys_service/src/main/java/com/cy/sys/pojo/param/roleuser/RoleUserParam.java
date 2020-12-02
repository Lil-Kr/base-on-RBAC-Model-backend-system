package com.cy.sys.pojo.param.roleuser;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * @Description:
 * @Author: CY
 * @Date: 2020/12/1
 */
@Data
@ToString
public class RoleUserParam {

    public interface GroupChangeRoleUsers {};

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 角色-用户id唯一主键
     */
    private Long surrogateId;

    /**
     * 角色id
     */
    @NotNull(groups = {Default.class},message = "角色roleId不为空")
    private Long roleId;

    /**
     * 多个角色id, 用逗号分隔
     */
    @NotNull(groups = {GroupChangeRoleUsers.class},message = "角色roleIds不为空")
    private String roleIds;

    /**
     * 用户id
     */
    @NotNull(groups = {GroupChangeRoleUsers.class, Default.class},message = "用户id不为空")
    private Long userId;
}
