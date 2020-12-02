package com.cy.sys.controller;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.roleacl.RoleAclSaveParam;
import com.cy.sys.service.ISysRoleAclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  [角色-权限关系]控制器
 * </p>
 *
 * @author CY
 * @since 2020-11-29
 */
@RestController
@RequestMapping("/sys/roleAcl")
@Slf4j
public class SysRoleAclController {

    @Resource
    private ISysRoleAclService sysRoleAclService1;

    /**
     * 修改角色对应的权限点
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("changeRoleAcls")
    public ApiResp changeRoleAcls(@RequestBody @Validated({RoleAclSaveParam.GroupChangeAcls.class}) RoleAclSaveParam param) throws Exception {
        return sysRoleAclService1.changeRoleAcls(param);
    }
}