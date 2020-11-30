package com.cy.sys.service.impl;

import com.cy.sys.pojo.entity.SysAcl;
import com.cy.sys.service.ISysCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SysCoreServiceImpl implements ISysCoreService {

    /**
     * 获取当前用户所拥有的权限列表
     * @param surrogateId
     * @return
     * @throws Exception
     */
    @Override
    public List<SysAcl> getCurrentUserAclList(Long surrogateId) throws Exception {

        return null;
    }

    /**
     * 获取角色 - 权限列表
     * @param surrogateId
     * @return
     * @throws Exception
     */
    @Override
    public List<SysAcl> getRoleAclList(Long surrogateId) throws Exception {
        return null;
    }

    /**
     * 获取用户权限列表
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<SysAcl> getUserAclList(Long userId) throws Exception {
        return null;
    }
}
