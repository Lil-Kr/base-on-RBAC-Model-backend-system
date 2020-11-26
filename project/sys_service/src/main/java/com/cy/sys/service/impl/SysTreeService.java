package com.cy.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.sys.dao.SysDeptMapper;
import com.cy.sys.pojo.dto.DeptLevelDto;
import com.cy.sys.pojo.entity.SysDept;
import com.cy.sys.util.LevelUtil;
import com.cy.sys.util.dept.DeptUtil;
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

    /**
     * 部门树
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

}
