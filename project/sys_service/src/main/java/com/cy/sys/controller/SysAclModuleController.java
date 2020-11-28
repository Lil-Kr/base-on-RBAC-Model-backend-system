package com.cy.sys.controller;


import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.aclmodule.AclModuleParam;
import com.cy.sys.service.ISysAclModuleService;
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
@RequestMapping("/sys/aclModule")
public class SysAclModuleController {


    @Resource
    private ISysAclModuleService sysAclModuleService1;

    @PostMapping("save")
    public ApiResp save(@RequestBody @Valid AclModuleParam param) throws Exception{

        sysAclModuleService1.add(param);

        sysAclModuleService1.edit(param);

        return null;
    }

}

