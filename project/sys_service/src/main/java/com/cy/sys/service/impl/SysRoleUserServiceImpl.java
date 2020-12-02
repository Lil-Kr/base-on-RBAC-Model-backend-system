package com.cy.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.sys.common.holder.RequestHolder;
import com.cy.sys.dao.SysRoleMapper;
import com.cy.sys.dao.SysRoleUserMapper;
import com.cy.sys.pojo.entity.SysRole;
import com.cy.sys.pojo.entity.SysRoleUser;
import com.cy.sys.pojo.param.roleuser.RoleUserParam;
import com.cy.sys.service.ISysRoleUserService;
import com.google.common.base.Splitter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private SysRoleUserMapper sysRoleUserMapper1;

    @Resource
    private SysRoleMapper sysRoleMapper1;

    @Override
    public ApiResp add(RoleUserParam param) throws Exception {
        return null;
    }

    @Override
    public ApiResp edit(RoleUserParam param) throws Exception {
        return null;
    }

    /**
     * 维护[角色-用户]关系接口
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp changeRoleUsers(RoleUserParam param) throws Exception {
        // 删除用户对应的角色
        QueryWrapper<SysRoleUser> delete = new QueryWrapper<>();
        delete.eq("user_id",param.getUserId());
        sysRoleUserMapper1.delete(delete);

        // 将需要修改的角色id转为 -> list
        List<String> roleIdlist = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(param.getRoleIds());

        // 根据角色id列表查询角色信息列表
        QueryWrapper<SysRole> query1 = new QueryWrapper<>();
        query1.in("surrogate_id",roleIdlist);
        List<SysRole> sysRoleList = sysRoleMapper1.selectList(query1);

        List<SysRoleUser> roleUsers = new ArrayList<>();
        String currentTime = DateUtil.getNowDateTime();
        sysRoleList.forEach(role -> {
            SysRoleUser roleUser = SysRoleUser.builder()
                    .surrogateId(IdWorker.getsnowFlakeId())
                    .roleId(role.getSurrogateId())
                    .userId(param.getUserId())
                    .operateIp("127.0.0.1")
                    .operator(RequestHolder.getCurrentUser().getLoginAccount())
                    .createTime(currentTime)
                    .updateTime(currentTime)
                    .build();
            roleUsers.add(roleUser);
        });

        // 批量更新角色-用户信息
        this.saveBatch(roleUsers);
        return ApiResp.success("更新用户角色信息成功");
    }
}
