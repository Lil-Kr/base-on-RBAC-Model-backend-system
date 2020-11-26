package com.cy.sys.controller;

import com.cy.sys.pojo.entity.SysUser;
import com.cy.sys.service.ISysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录
 * @author CY
 * @since 2020-11-27
 */
@RestController
@RequestMapping("/sys/userLogin")
public class UserLoginController {

    @Resource
    private ISysUserService sysUserService1;

    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 获取用户名密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 查询数据库是否存在用户信息
        SysUser user = sysUserService1.findByKeyWord(username);

        String msg = "";

//        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
//            msg = "用户名或密码不能为空";
//        }else if (Objects.isNull(user)) {
//            msg = "找不到用户";
//        }else if () {
//
//        }

    }
}
