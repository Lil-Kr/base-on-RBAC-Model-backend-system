package com.cy.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.user.UserDelParam;
import com.cy.sys.pojo.param.user.UserListPageParam;
import com.cy.sys.pojo.param.user.UserSaveParam;
import com.cy.sys.pojo.vo.user.SysUserVo;
import com.cy.sys.service.ISysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
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

        IPage<SysUserVo> page = new Page<>();
        page.setCurrent(param.getCurrent());
        page.setSize(param.getCount());
        IPage<SysUserVo> userVoIPage = sysUserService1.listPage(page, param);
        return ApiResp.success(userVoIPage);
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
}