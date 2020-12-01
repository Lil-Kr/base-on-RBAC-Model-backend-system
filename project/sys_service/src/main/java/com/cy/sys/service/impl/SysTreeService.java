package com.cy.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.sys.dao.SysAclMapper;
import com.cy.sys.dao.SysAclModuleMapper;
import com.cy.sys.dao.SysDeptMapper;
import com.cy.sys.pojo.dto.acl.AclDto;
import com.cy.sys.pojo.dto.aclmodule.AclModuleDto;
import com.cy.sys.pojo.dto.dept.DeptLevelDto;
import com.cy.sys.pojo.entity.SysAcl;
import com.cy.sys.pojo.entity.SysAclModule;
import com.cy.sys.pojo.entity.SysDept;
import com.cy.sys.service.ISysCoreService;
import com.cy.sys.util.acl.AclUtil;
import com.cy.sys.util.aclmodule.AclModuleUtil;
import com.cy.sys.util.dept.DeptUtil;
import com.cy.sys.util.dept.LevelUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysTreeService {

    @Resource
    private SysDeptMapper sysDeptMapper1;

    @Resource
    private SysAclModuleMapper sysAclModuleMapper1;

    @Resource
    private SysAclMapper sysAclMapper1;

    @Resource
    private ISysCoreService sysCoreService1;

    /**
     * 获取部门树
     * @return
     */
    public List<DeptLevelDto> deptTree() {
        // 查询所有部门信息
        List<SysDept> deptList = sysDeptMapper1.selectList(new QueryWrapper());

        // 实体集合转为Dto集合
        List<DeptLevelDto> dtoList = deptList.stream().map(dept -> DeptLevelDto.adapt(dept)).collect(Collectors.toList());
        return deptListToTree(dtoList);
    }

    /**
     * 递归组装tree
     * @param dtoList 数据库中的所有部门信息
     * @return
     */
    public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> dtoList) {

        if (CollectionUtils.isEmpty(dtoList)) {
            return new ArrayList<>();
        }

        // 获取一级部门 rootList
        List<DeptLevelDto> rootList = dtoList.stream()
                .filter(deptLevelDto -> LevelUtil.ROOT.equals(deptLevelDto.getLevel()))// 过滤出顶层部门信息
                .sorted(Comparator.comparing(DeptLevelDto::getSeq)) // 按照seq字段升序排序
                .collect(Collectors.toList());

        // 按照level分组
        Map<String, List<DeptLevelDto>> levelDeptMap = dtoList.stream()
                .sorted(Comparator.comparing(SysDept::getSeq)) // 按照seq字段升序排序
                .collect(Collectors.groupingBy(dept -> dept.getLevel()));

        // 从顶层开始递归生成部门树
        transformDeptTree(rootList,LevelUtil.ROOT,levelDeptMap);
        return rootList;
    }

    /**
     * 将部门树转为树结构
     * @param levelDtoList
     * @param level
     * @param levelDeptMap
     */
    public void transformDeptTree(List<DeptLevelDto> levelDtoList,String level,Map<String, List<DeptLevelDto>> levelDeptMap) {

        levelDtoList.forEach(deptLevelDto -> {
            /**
             * 处理当前层级数据
             * **/
            // 计算出下一级的level
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());// 0.1

            // 获得下一级的所有部门信息
            List<DeptLevelDto> dtoNextTempList = levelDeptMap.get(nextLevel);// 大可 中台

            if (CollectionUtils.isEmpty(dtoNextTempList)) {// 没有下一级了
                return;
            }

            // 排序
            Collections.sort(dtoNextTempList, DeptUtil.deptLevelDtoComparator);
            // 设置下一层部门
            deptLevelDto.setDeptList(dtoNextTempList);

            // 进入下一层进行递归处理
            transformDeptTree(dtoNextTempList,nextLevel,levelDeptMap);
        });
    }

    /** 获取权限模块树 ============================== **/

    /**
     * 获取权限模块树
     * @return
     */
    public List<AclModuleDto> aclModuleTree() {
        // 查询所有权限模块信息
        List<SysAclModule> aclModuleList = sysAclModuleMapper1.selectList(new QueryWrapper());

        // 实体集合转为Dto集合
        List<AclModuleDto> dtoList = aclModuleList.stream().map(aclModule -> AclModuleDto.adapt(aclModule)).collect(Collectors.toList());

        return aclModuleListToTree(dtoList);
    }

    private List<AclModuleDto> aclModuleListToTree(List<AclModuleDto> dtoList) {

        if (CollectionUtils.isEmpty(dtoList)) {
            return new ArrayList<>();
        }

        // 获取一级部门 rootList
        List<AclModuleDto> rootList = dtoList.stream()
                .filter(dto -> LevelUtil.ROOT.equals(dto.getLevel()))// 过滤出顶层部门信息
                .sorted(Comparator.comparing(AclModuleDto::getSeq)) // 按照seq字段升序排序
                .collect(Collectors.toList());

        // 按照level分组
        Map<String, List<AclModuleDto>> levelAclModuleMap = dtoList.stream()
                .sorted(Comparator.comparing(AclModuleDto::getSeq)) // 按照seq字段升序排序
                .collect(Collectors.groupingBy(dto -> dto.getLevel()));

        // 从顶层开始递归生成部门树
        transformAclModuleTree(rootList,LevelUtil.ROOT,levelAclModuleMap);
        return rootList;
    }

    private void transformAclModuleTree(List<AclModuleDto> levelAclModuleList, String level, Map<String, List<AclModuleDto>> levelAclModuleMap) {

        levelAclModuleList.forEach(aclModuleDto -> {
            /**
             * 处理当前层级数据
             * **/
            // 计算出下一级的level
            String nextLevel = LevelUtil.calculateLevel(level, aclModuleDto.getId());// 0.1

            // 获得下一级的所有部门信息
            List<AclModuleDto> dtoNextTempList = levelAclModuleMap.get(nextLevel);//

            if (CollectionUtils.isEmpty(dtoNextTempList)) {// 没有下一级了
                return;
            }

            // 排序
            Collections.sort(dtoNextTempList, AclModuleUtil.aclModuleLevelDtoComparator);
            // 设置下一层部门
            aclModuleDto.setAclModuleDtoList(dtoNextTempList);

            // 进入下一层进行递归处理
            transformAclModuleTree(dtoNextTempList,nextLevel,levelAclModuleMap);
        });
    }

    /** 获取权限模块与权限点组成的树 ============================== **/

    /**
     * 获取角色对应的权限树
     * @param roleSurrogateId 角色id
     * @return
     * @throws Exception
     */
    public List<AclModuleDto> roleTree(Long roleSurrogateId) throws Exception{
        // 1. 拿到当前用户已经被分配过的权限点
        List<SysAcl> userAclList = sysCoreService1.getCurrentUserAclList();

        // 2. 当前角色分配过的权限点
        List<SysAcl> roleAclList = sysCoreService1.getRoleAclList(roleSurrogateId);

        // 3. 存放用户已分配的权限id(AclId), 去重
        Set<Long> userAclIdSet = userAclList.stream().map(acl -> acl.getSurrogateId()).collect(Collectors.toSet());

        // 4. 角色已分配权限的id集合, 去重
        Set<Long> roleAclSet = roleAclList.stream().map(roleAcl -> roleAcl.getSurrogateId()).collect(Collectors.toSet());

        // 5. 获取所有的权限点, list -> set
        List<SysAcl> aclList = sysAclMapper1.selectList(new QueryWrapper<>());
        Set<SysAcl> aclAllSet = Sets.newHashSet(aclList);

        // 将权限点列表为当前用户标记出访问权限
        List<AclDto> aclDtoList = Lists.newArrayList();
        aclList.stream()
                .map(acl -> AclDto.adapt(acl))
                .forEach(aclDto -> {
                    // 打标已分配的权限点
                    if (userAclIdSet.contains(aclDto.getSurrogateId())) {
                        aclDto.setHasAcl(true);
                    }

                    // 打标用户分配权限时的操作权限(是否有权限操作)
                    if (roleAclSet.contains(aclDto.getSurrogateId())) {
                        aclDto.setChecked(true);
                    }
                    aclDtoList.add(aclDto);
                });

        // 将权限点与权限模块组装为树结构
        return aclListToTree(aclDtoList);
    }

    /**
     * 获取权限模块以及模块下面的权限点明细
     * @param aclDtoList
     * @return
     */
    private List<AclModuleDto> aclListToTree (List<AclDto> aclDtoList) {
        if (CollectionUtils.isEmpty(aclDtoList)) {
            return Lists.newArrayList();
        }
        // 拿到权限模块系统权限树
        List<AclModuleDto> aclModuleDtoList = this.aclModuleTree();

        // 根据[权限模块id]分组
        Map<Long, List<AclDto>> moduleIdAclMap = aclDtoList.stream()
                .filter(aclDto -> aclDto.getStatus() == 0)
                .collect(Collectors.groupingBy(aclDto -> aclDto.getAclModuleId()));

        // 绑定权限点到权限模块下
        this.bindAclsWithOrder(aclModuleDtoList,moduleIdAclMap);
        return aclModuleDtoList;
    }

    /**
     * 递归绑定权限点到权限模块下
     * @param aclModuleDtoList
     * @param moduleIdAclMap
     */
    public void bindAclsWithOrder(List<AclModuleDto> aclModuleDtoList,Map<Long, List<AclDto>> moduleIdAclMap) {
        if (CollectionUtils.isEmpty(aclModuleDtoList)) {
            return;
        }

        aclModuleDtoList.forEach(aclModuleDto -> {
            List<AclDto> aclDtoList = moduleIdAclMap.get(aclModuleDto.getSurrogateId());
            // 如果权限点列表不为空就绑定到权限模块上面
            if (CollectionUtils.isNotEmpty(aclDtoList)) {
                // 根据seq排序
                Collections.sort(aclDtoList, AclUtil.aclDtoComparator);
                // 将排序好的权限点列表放到对应的权限模块下
                aclModuleDto.setAclDtoList(aclDtoList);
            }
            // 递归下一级的权限点和权限模块
            bindAclsWithOrder(aclModuleDto.getAclModuleDtoList(),moduleIdAclMap);
        });
    }

}
