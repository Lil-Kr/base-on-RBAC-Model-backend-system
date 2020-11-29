package com.cy.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.sys.dao.SysRoleMapper;
import com.cy.sys.pojo.entity.SysRole;
import com.cy.sys.pojo.param.role.RoleDeleteParam;
import com.cy.sys.pojo.param.role.RoleListPageParam;
import com.cy.sys.pojo.param.role.RoleSaveParam;
import com.cy.sys.pojo.vo.role.SysRoleVo;
import com.cy.sys.service.ISysRoleService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 *  角色服务实现类
 * </p>
 *
 * @author CY
 * @since 2020-11-26
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper1;

    /**
     * 添加角色信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp add(RoleSaveParam param) throws Exception {
        if (checkExit(param.getSurrogateId(),param.getName(),param.getType())) {
            ApiResp.failure("角色名已存在");
        }

        Long surrogateId = IdWorker.getsnowFlakeId(); // surrogateId
        String currentTime = DateUtil.getNowDateTime();// 当前时间
        SysRole role = SysRole.builder()
                .surrogateId(surrogateId)
                .name(param.getName())
                .type(param.getType())
                .deleted(0)
                .remark(param.getRemark())
                .operator("system") // todo 操作人 系统时间 ip
                .operateIp("127.0.0.1")
                .createTime(currentTime)
                .updateTime(currentTime)
                .build();

        sysRoleMapper1.insert(role);
        return ApiResp.success("添加角色信息成功");
    }

    /**
     * 修改角色信息
     * @param param
     * @throws Exception
     */
    @Override
    public ApiResp edit(RoleSaveParam param) throws Exception {
        if (checkExit(param.getSurrogateId(),param.getName(),param.getType())) {
            ApiResp.failure("角色名已存在");
        }

        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.eq("surrogate_id",param.getSurrogateId());
        SysRole before = sysRoleMapper1.selectOne(query);
        Preconditions.checkNotNull(before, "待更新的角色信息不存在");

        SysRole after = SysRole.builder()
                .id(before.getId())
                .surrogateId(param.getSurrogateId())
                .name(param.getName())
                .type(param.getType())
                .operator("system") // todo 操作人 系统时间 ip
                .operateIp("127.0.0.1")
                .updateTime(DateUtil.getNowDateTime())
                .build();

        int update = sysRoleMapper1.update(after, query);
        if (update >= 1) {
            return ApiResp.success("修改角色信息成功");
        }else {
            return ApiResp.failure("修改角色信息失败");
        }
    }

    /**
     * 分页查询角色列表
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp listPage(RoleListPageParam param) throws Exception {
        Page<SysRoleVo> page = new Page<>();
        page.setCurrent(param.getCurrent());
        page.setSize(param.getSize());

        IPage<SysRoleVo> iPage = sysRoleMapper1.selectRoleListPage(page, param);
        return ApiResp.success(iPage);
    }

    /**
     * 根据类型姓名检查同一类型下是否有相同名称的角色
     * @param surrogateId 父id
     * @param name 角色名称
     * @param type 角色类型
     */
    protected boolean checkExit(Long surrogateId,String name,Integer type) {
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        if (Objects.nonNull(surrogateId)) {
            query.eq("surrogate_id",surrogateId);
        }
        query.eq("type",type);
        query.eq("name",name);
        Integer count = sysRoleMapper1.selectCount(query);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 删除角色信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp delete(RoleDeleteParam param) throws Exception {
        QueryWrapper<SysRole> query = new QueryWrapper();
        query.eq("surrogate_id",param);
        SysRole role = sysRoleMapper1.selectOne(query);
        if (Objects.isNull(role)) {
            return ApiResp.failure("角色不存在, 删除失败");
        }

        //修改删除状态
        role.setDeleted(1);
        int update = sysRoleMapper1.updateById(role);
        if (update >= 1) {
            return ApiResp.success("删除角色成功");
        }else {
            return ApiResp.failure("删除角色失败");
        }
    }
}
