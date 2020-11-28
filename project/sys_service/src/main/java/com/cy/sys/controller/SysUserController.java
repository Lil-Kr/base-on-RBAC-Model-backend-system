package com.cy.sys.controller;


import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.user.UserDelParam;
import com.cy.sys.pojo.param.user.UserListPageParam;
import com.cy.sys.pojo.param.user.UserSaveParam;
import com.cy.sys.pojo.param.user.UserUpdatePwdParam;
import com.cy.sys.service.ISysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author CY
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService1;

    /**
     * 分页查询列表
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("listPage")
    public ApiResp listPage(@RequestBody @Valid UserListPageParam param) throws Exception {
        return sysUserService1.listPage(param);
    }

    /**
     * 保存用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    public ApiResp save(@RequestBody @Valid UserSaveParam param) throws Exception {

        if (Objects.nonNull(param.getId()) && Objects.nonNull(param.getSurrogateId())) {// update
            return sysUserService1.edit(param);
        }else { // insert
            return sysUserService1.add(param);
        }
    }

    /**
     * 删除用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("delete")
    public ApiResp delete(@RequestBody @Valid UserDelParam param) throws Exception {
        return sysUserService1.delete(param);
    }

    /**
     * 修改用户密码
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("updatePassword")
    public ApiResp updatePassword(@RequestBody @Valid UserUpdatePwdParam param) throws Exception {
        return sysUserService1.updatePassword(param);

    }
}