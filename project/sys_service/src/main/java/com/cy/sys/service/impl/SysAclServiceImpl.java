package com.cy.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.sys.dao.SysAclMapper;
import com.cy.sys.pojo.entity.SysAcl;
import com.cy.sys.pojo.param.acl.AclPageParam;
import com.cy.sys.pojo.param.acl.AclParam;
import com.cy.sys.pojo.vo.acl.SysAclVo;
import com.cy.sys.service.ISysAclService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author CY
 * @since 2020-11-26
 */
@Service
@Slf4j
public class SysAclServiceImpl extends ServiceImpl<SysAclMapper, SysAcl> implements ISysAclService {

    @Resource
    private SysAclMapper sysAclMapper1;

    /**
     * 添加权限点
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp add(AclParam param) throws Exception {
        if (checkAclExist(param.getAclModuleId(),param.getName(),param.getSurrogateId())) {
            return ApiResp.failure("待添加的权限点名不能重复");
        }
        Long surrogateId = IdWorker.getsnowFlakeId(); // surrogateId
        String currentTime = DateUtil.getNowDateTime();// 当前时间
        SysAcl acl = SysAcl.builder()
                .surrogateId(surrogateId)
                .number("ACL" + surrogateId)
                .name(param.getName())
                .aclModuleId(param.getAclModuleId())
                .url(param.getUrl())
                .type(param.getType())
                .status(param.getStatus())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .operator("System")
                .operateIp("127.0.0.1")
                .createTime(currentTime)
                .updateTime(currentTime)
                .build();

        sysAclMapper1.insert(acl);
        return ApiResp.success("添加权限点成功");
    }

    /**
     * 更新权限点
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp edit(AclParam param) throws Exception {
        if (checkAclExist(param.getAclModuleId(),param.getName(),param.getSurrogateId())) {
            return ApiResp.failure("待添加的权限点名不能重复");
        }

        QueryWrapper<SysAcl> query1 = new QueryWrapper();
        query1.eq("surrogate_id",param.getSurrogateId());
        SysAcl before = sysAclMapper1.selectOne(query1);
        Preconditions.checkNotNull(before, "带更新的权限点不存在");

        SysAcl after = SysAcl.builder()
                .id(before.getId())
                .surrogateId(before.getSurrogateId())
                .number(before.getNumber())
                .aclModuleId(param.getAclModuleId())
                .url(param.getUrl())
                .type(param.getType())
                .status(param.getStatus())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .updateTime(DateUtil.getNowDateTime())
                .build();

        sysAclMapper1.updateById(after);
        return ApiResp.success("更新权限点成功");
    }

    /**
     * 分页查询权限点列表
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp listPage(AclPageParam param) throws Exception {
        Page<SysAclVo> page = new Page<>(param.getCurrent(), param.getSize());
        page.setCurrent(param.getCurrent());
        page.setSize(param.getSize());
        IPage<SysAclVo> iPage = sysAclMapper1.selectAclListPage(page, param);
        return ApiResp.success(iPage);
    }

    /**
     * 判断同一个权限模块下是否存在相同的名称的权限点
     * @param aclModuleId
     * @param name
     * @param surrogateId
     * @return
     */
    protected boolean checkAclExist(Long aclModuleId,String name,Long surrogateId) {
        QueryWrapper<SysAcl> query = new QueryWrapper<>();
        if (Objects.nonNull(surrogateId)) {
            query.eq("surrogate_id", surrogateId);
        }
        query.eq("acl_module_id", aclModuleId);
        query.eq("name", name);

        Integer count = sysAclMapper1.selectCount(query);

        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }


}
