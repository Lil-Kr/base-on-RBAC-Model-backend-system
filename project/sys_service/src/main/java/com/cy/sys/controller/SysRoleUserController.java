package com.cy.sys.controller;


import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.roleuser.RoleUserParam;
import com.cy.sys.service.ISysRoleUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  [角色-用户]关系
 * </p>
 * @author CY
 * @since 2020-11-29
 */
@RestController
@RequestMapping("/sys/roleUser")
public class SysRoleUserController {

    @Resource
    private ISysRoleUserService sysRoleUserService1;

//    /**
//     * 新增 [角色-用户]关系信息
//     * @return
//     * @throws Exception
//     */
//    @PostMapping("add")
//    public ApiResp add(@RequestBody @Validated({Default.class}) RoleUserParam param) throws Exception {
//        return sysRoleUserService1.add(param);
//    }
//
//    /**
//     * 修改 [角色-用户]关系信息
//     * @return
//     * @throws Exception
//     */
//    @PostMapping("edit")
//    public ApiResp edit(@RequestBody @Valid RoleUserParam param) throws Exception {
//
//        return sysRoleUserService1.edit(param);
//    }

    /**
     * 获取角色分配的用户列表
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("roleUserList")
    public ApiResp roleUserList(@RequestBody @Validated({RoleUserParam.GroupRoleUserPageList.class}) RoleUserParam param) throws Exception {
        return sysRoleUserService1.roleUserList(param);
    }

    /**
     * 维护[角色-用户]关系接口
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("changeRoleUsers")
    public ApiResp changeRoleUsers(@RequestBody @Validated({RoleUserParam.GroupChangeRoleUsers.class}) RoleUserParam param) throws Exception {
        return sysRoleUserService1.changeRoleUsers(param);
    }
}

