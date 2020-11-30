package com.cy.sys.controller;


import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.role.RoleDeleteParam;
import com.cy.sys.pojo.param.role.RoleListPageParam;
import com.cy.sys.pojo.param.role.RoleSaveParam;
import com.cy.sys.service.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * <p>
 *  角色模块
 * </p>
 *
 * @author CY
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/sys/role")
@Slf4j
public class SysRoleController {

    @Resource
    private ISysRoleService sysRoleService1;

    /**
     * 分页查询角色列表
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("listPage")
    public ApiResp listPage (@RequestBody @Valid RoleListPageParam param) throws Exception {
        return sysRoleService1.listPage(param);
    }

    /**
     * 保存角色信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    public ApiResp save (@RequestBody @Valid RoleSaveParam param) throws Exception {
        if (Objects.isNull(param.getSurrogateId())) {
            return sysRoleService1.add(param);
        }else {
            return sysRoleService1.edit(param);
        }
    }

    /**
     * 删除角色信息
     * @param param
     */
    @PostMapping("delete")
    public ApiResp delete (@RequestBody @Valid RoleDeleteParam param) throws Exception {
        return sysRoleService1.delete(param);
    }

    /**
     * 获取权限树
     * @param roleId
     * @return
     * @throws Exception
     */
    @PostMapping("roleTree")
    public ApiResp roleTree(@RequestParam("roleId") @Valid Integer roleId) throws Exception {

        return null;
    }

}

