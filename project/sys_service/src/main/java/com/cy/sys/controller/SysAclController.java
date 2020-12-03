package com.cy.sys.controller;


import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.acl.AclPageParam;
import com.cy.sys.pojo.param.acl.AclParam;
import com.cy.sys.service.ISysAclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CY
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/sys/acl")
@Slf4j
public class SysAclController {

    @Resource
    private ISysAclService sysAclService1;

    /**
     * 分页查询权限点列表
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("listPage")
    public ApiResp listPage(@RequestBody @Valid AclPageParam param) throws Exception {
        return sysAclService1.listPage(param);
    }

    /**
     * 权限点信息保存
     * @param param
     * @return
     */
    @PostMapping("add")
    public ApiResp add(@RequestBody @Valid AclParam param) throws Exception {
        return sysAclService1.add(param);
    }

    /**
     * 修改权限点信息
     * @param param
     * @return
     */
    @PostMapping("edit")
    public ApiResp edit(@RequestBody @Valid AclParam param) throws Exception {
        return sysAclService1.edit(param);
    }

    /**
     * 获取权限点分配的用户角色
     */
    @PostMapping("acls")
    public ApiResp acls(@RequestBody @Validated({AclParam.GroupAcls.class}) AclParam param) throws Exception {
        return sysAclService1.acls(param);
    }

}

