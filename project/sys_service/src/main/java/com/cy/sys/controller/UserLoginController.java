package com.cy.sys.controller;

import cn.hutool.crypto.SecureUtil;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.sys.pojo.entity.SysUser;
import com.cy.sys.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Objects;

/**
 * 用户登录
 * @author CY
 * @since 2020-11-27
 */
@RestController
@RequestMapping("/userLogin")
public class UserLoginController {

    @Resource
    private ISysUserService sysUserService1;

    /**
     * 用户登录
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("login")
    public ApiResp login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取用户名密码
        String username = request.getParameter("login_account");
        String password = request.getParameter("password");

        // 查询数据库是否存在用户信息
        SysUser user = sysUserService1.findByKeyWord(username);

        String msg = "";
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            msg = "用户名或密码不能为空";
        }else if (Objects.isNull(user)) {
            msg = "找不到用户";
        }else if (!user.getPassword().equals(SecureUtil.md5(password))) {
            msg = "用户输入的密码不正确";
        }else if (user.getStatus() == 1 || user.getDeleted() == 1) {
            msg = "用户账号已冻结或已删除, 请联系管理员";
        }
        if (StringUtils.isNotEmpty(msg)) {
            return ApiResp.failure(msg);
        }

        // TODO 前端做跳转 进入系统首页
        String token = SecureUtil.md5(username + password + DateUtil.getCurrentDateTimeMilli());
        HashMap<String,Object> userLoginInfo = new HashMap();
        userLoginInfo.put("login_account",user.getLoginAccount());
        userLoginInfo.put("token", token);
        return ApiResp.success("登录成功",userLoginInfo);
    }

    /**
     * 用户退出
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("loginOut")
    public ApiResp loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // todo 用户退出登录接口
        return ApiResp.success("");
    }

}
