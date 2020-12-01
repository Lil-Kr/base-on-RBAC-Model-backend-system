package com.cy.sys.service.impl;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.entity.SysRoleUser;
import com.cy.sys.dao.SysRoleUserMapper;
import com.cy.sys.pojo.param.roleuser.RoleUserParam;
import com.cy.sys.service.ISysRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CY
 * @since 2020-11-26
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements ISysRoleUserService {

    @Override
    public ApiResp add(RoleUserParam param) throws Exception {
        return null;
    }

    @Override
    public ApiResp edit(RoleUserParam param) throws Exception {
        return null;
    }
}
