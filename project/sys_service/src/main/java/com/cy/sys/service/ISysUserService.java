package com.cy.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.entity.SysUser;
import com.cy.sys.pojo.param.user.UserDelParam;
import com.cy.sys.pojo.param.user.UserListPageParam;
import com.cy.sys.pojo.param.user.UserSaveParam;
import com.cy.sys.pojo.vo.user.SysUserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CY
 * @since 2020-11-26
 */
public interface ISysUserService extends IService<SysUser> {

    ApiResp edit(UserSaveParam param) throws Exception;

    ApiResp add(UserSaveParam param) throws Exception;

    ApiResp delete(UserDelParam param) throws Exception;

    IPage<SysUserVo> listPage(IPage<SysUserVo> page, UserListPageParam param) throws Exception;
}
