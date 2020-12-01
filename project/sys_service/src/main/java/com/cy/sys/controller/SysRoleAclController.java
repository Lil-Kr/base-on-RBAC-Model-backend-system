package com.cy.sys.controller;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.roleacl.RoleAclSaveParam;
import com.cy.sys.service.ISysRoleAclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

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
     * [角色-权限]控制保存
     * @param param
     */
    @PostMapping("save")
    public ApiResp save (@RequestBody @Valid RoleAclSaveParam param) throws Exception {
        if (Objects.isNull(param.getSurrogateId())) {// insert
            return sysRoleAclService1.add(param);
        }else {// update
            return sysRoleAclService1.edit(param);
        }
    }

}