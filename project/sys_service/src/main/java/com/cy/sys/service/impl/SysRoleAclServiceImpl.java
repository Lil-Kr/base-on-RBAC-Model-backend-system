package com.cy.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.entity.SysRoleAcl;
import com.cy.sys.dao.SysRoleAclMapper;
import com.cy.sys.pojo.param.roleacl.RoleAclSaveParam;
import com.cy.sys.service.ISysRoleAclService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  角色权限服务实现类
 * </p>
 * @author CY
 * @since 2020-11-26
 */
@Service
@Slf4j
public class SysRoleAclServiceImpl extends ServiceImpl<SysRoleAclMapper, SysRoleAcl> implements ISysRoleAclService {

    @Resource
    private SysRoleAclMapper sysRoleAclMapper1;

    /**
     * 添加 角色-权限
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp add(RoleAclSaveParam param) throws Exception {
        if (checkRoleAclExist(param.getRoleId(),param.getAclId())) {
            return ApiResp.failure("同一个角色不能赋予同一个权限点");
        }




        return null;
    }

    /**
     * 修改 角色-权限
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp edit(RoleAclSaveParam param) throws Exception {
        return null;
    }

    /**
     * 检查同一个角色下不能分配同一个权限
     * @param roleId
     * @param aclId
     * @return
     */
    protected boolean checkRoleAclExist(Long roleId, Long aclId) {
        QueryWrapper<SysRoleAcl> query1 = new QueryWrapper<>();
        query1.eq("role_id",roleId);
        query1.eq("acl_id",aclId);
        Integer count = sysRoleAclMapper1.selectCount(query1);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

}
