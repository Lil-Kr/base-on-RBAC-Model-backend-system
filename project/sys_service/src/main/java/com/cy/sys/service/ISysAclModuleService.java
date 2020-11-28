package com.cy.sys.service;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.entity.SysAclModule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.sys.pojo.param.aclmodule.AclModuleParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CY
 * @since 2020-11-26
 */
public interface ISysAclModuleService extends IService<SysAclModule> {

    ApiResp add(AclModuleParam param) throws Exception;

    ApiResp edit(AclModuleParam param) throws Exception;

    ApiResp aclModuleTree() throws Exception;

}
