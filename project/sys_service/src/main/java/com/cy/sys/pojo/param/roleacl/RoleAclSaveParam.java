package com.cy.sys.pojo.param.roleacl;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class RoleAclSaveParam {

    /**
     * 角色-权限id唯一主键
     */
    private Long surrogateId;

    /**
     * 角色id
     */
    @NotNull(message = "角色id不为空")
    private Long roleId;

    /**
     * 权限id
     */
    @NotNull(message = "权限id不为空")
    private Long aclId;
}
