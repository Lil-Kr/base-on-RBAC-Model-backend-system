package com.cy.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.sys.common.holder.RequestHolder;
import com.cy.sys.dao.SysDeptMapper;
import com.cy.sys.pojo.dto.dept.DeptLevelDto;
import com.cy.sys.pojo.entity.SysDept;
import com.cy.sys.pojo.param.dept.DeptDeleteParam;
import com.cy.sys.pojo.param.dept.DeptGetChildrenParam;
import com.cy.sys.pojo.param.dept.DeptListAllParam;
import com.cy.sys.pojo.param.dept.DeptParam;
import com.cy.sys.service.ISysDeptService;
import com.cy.sys.util.dept.DeptUtil;
import com.cy.sys.util.dept.LevelUtil;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author CY
 * @since 2020-11-25
 */
@Service
@Slf4j
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper1;

    @Resource
    private SysTreeService sysTreeService1;

    /**
     * 添加部门信息
     * @param param
     * @return
     */
    @Override
    public ApiResp add(DeptParam param) throws Exception {
        /**
         * 检查部门名是否相同
         */
        if (checkDeptExist(param.getParentSurrogateId(),param.getName(),param.getSurrogateId())) {// 检查
            return ApiResp.error("待添加的部门名不能重复");
        }

        /**计算层级**/
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId());
        Long surrogateId = IdWorker.getsnowFlakeId(); // surrogateId
        String currentTime = DateUtil.getNowDateTime();// 当前时间
        SysDept dept = SysDept.builder()
                .surrogateId(surrogateId)
                .number("DEPT"+ surrogateId)
                .parentId(param.getParentSurrogateId())
                .seq(param.getSeq())
                .level(level)
                .name(param.getName())
                .remark(param.getRemark())
                .createTime(currentTime)
                .updateTime(currentTime)
                .operator(RequestHolder.getCurrentUser().getUserName())
                .operateIp("127.0.0.1")
                .build();

        sysDeptMapper1.insert(dept);
        return ApiResp.success("添加成功");
    }

    /**
     * 更新部门信息
     * @param param
     * @return
     */
    @Override
    public ApiResp edit(DeptParam param) throws Exception {
        if (checkDeptExist(param.getParentSurrogateId(),param.getName(),param.getSurrogateId())) {// 检查部门名是否重复
            return ApiResp.error("待更新的部门名不能重复");
        }

        // 检查待更新的部门是否存在
        SysDept before = sysDeptMapper1.selectById(param.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");

        // 更新当前部门
        SysDept after = SysDept.builder()
                .id(before.getId())
                .surrogateId(before.getSurrogateId())
                .name(param.getName())
                .parentId(param.getParentSurrogateId())// 上级部门id
                .seq(param.getSeq())
                .level(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()))
                .remark(param.getRemark())
                .updateTime(DateUtil.getNowDateTime())
                .operator(RequestHolder.getCurrentUser().getUserName())
                .operateIp("127.0.0.1")
                .build();

        // 更新子部门信息
        this.updateWithChildDept(before,after);
        return ApiResp.success("更新部门成功");
    }

    /**
     * 更新当前部门的子部门信息
     * @param before 旧部门
     * @param after 新部门
     */
    @Transactional
    protected void updateWithChildDept(SysDept before, SysDept after) {
        // 修改当前部门信息
        sysDeptMapper1.updateById(after);
        // 更新当前部门的子部门
        String newLevelPrefix = after.getLevel();// 0.1.3
        String oldLevelPrefix = before.getLevel();// 0.1
        if (!newLevelPrefix.equals(oldLevelPrefix)) {// 不一致需要做子部门的更新
            this.updateChildDeptTree(after);
        }
    }

    /**
     * 递归变更部门树层级, 并维护子部门的level
     */
    protected void updateChildDeptTree(SysDept afterDept) {
        List<SysDept> deptList = sysDeptMapper1.selectChildDeptListByParentId(afterDept.getSurrogateId());
        if (CollectionUtils.isEmpty(deptList)) {
            return;
        }

        deptList.forEach(dept -> {
            dept.setLevel(LevelUtil.calculateLevel(afterDept.getLevel(),afterDept.getId()));
            dept.setUpdateTime(DateUtil.getNowDateTime());
            updateChildDeptTree(dept);
        });
        // 操作db
        this.updateBatchById(deptList);
    }

    /**
     * 获取当前部门信息及子部门信息
     * @param dto
     * @return
     */
    @Override
    public ApiResp getChildrenDeptList(DeptGetChildrenParam dto) throws Exception {
        // 当前部门
        QueryWrapper<SysDept> query1 = new QueryWrapper<>();
        query1.eq("surrogate_id",dto.getSurrogateId());
        SysDept dept = sysDeptMapper1.selectOne(query1);

        // 子部门
        QueryWrapper<SysDept> query2 = new QueryWrapper<>();
        List<SysDept> deptChildrenList = sysDeptMapper1.selectList(query2);
        deptChildrenList.add(dept);

        // 排序
        Collections.sort(deptChildrenList, DeptUtil.deptByIdComparator);
        return ApiResp.success(deptChildrenList);
    }

    /**
     * 获取部门树信息
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp deptTree() throws Exception {
        List<DeptLevelDto> dtoList = sysTreeService1.deptTree();
        return ApiResp.success(dtoList);
    }

    /**
     * 删除部门
     * @param param
     * @return
     */
    @Override
    public ApiResp delete(DeptDeleteParam param) throws Exception {
        QueryWrapper<SysDept> query = new QueryWrapper<>();
        query.eq("surrogate_id", param.getSurrogateId());
        SysDept dept = sysDeptMapper1.selectOne(query);
        if (Objects.isNull(dept)) {
            return ApiResp.failure("待删除的部门不存在");
        }

        // 检查要删除的部门下面是否还有子部门
        QueryWrapper<SysDept> query1 = new QueryWrapper<>();
        query1.eq("parent_id", param.getSurrogateId());
        Integer count = sysDeptMapper1.selectCount(query1);
        if (count >= 1) {
            return ApiResp.failure("待删除的部门下存在子部门, 不能删除");
        }

        sysDeptMapper1.deleteById(dept.getId());
        return ApiResp.success("删除部门成功");
    }

    /**
     * 查询所有部门信息
     * @return
     */
    @Override
    public ApiResp deptListAll(DeptListAllParam param) throws Exception {
        QueryWrapper<SysDept> query1 = new QueryWrapper<>();
        query1.like("name",param.getName());
        List<SysDept> deptList = sysDeptMapper1.selectList(query1);
        Collections.sort(deptList,DeptUtil.deptComparator);
        return ApiResp.success(deptList);
    }

    /**
     * 校验同一级部门下不能有名称重复的部门
     * @param parentId
     * @param deptName
     * @param SurrogateId
     * @return true/false
     */
    private boolean checkDeptExist(Long parentId,String deptName,Long SurrogateId) {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        if (Objects.nonNull(deptName)) {
            queryWrapper.eq("name",deptName);
        }
        if (Objects.nonNull(SurrogateId)) {
            queryWrapper.eq("surrogate_id",SurrogateId);
        }
        Integer count = sysDeptMapper1.selectCount(queryWrapper);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取当前部门所在层级的level
     * @param deptId
     * @return
     */
    private String getLevel(Long deptId) {
        SysDept dept = sysDeptMapper1.selectById(deptId);
        if (Objects.isNull(dept)) {
            return null;
        }else {
            return dept.getLevel();
        }
    }

}
