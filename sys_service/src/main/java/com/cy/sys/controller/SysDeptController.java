package com.cy.sys.controller;


import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.dept.DeptDeleteParam;
import com.cy.sys.pojo.param.dept.DeptGetChildrenParam;
import com.cy.sys.pojo.param.dept.DeptListAllParam;
import com.cy.sys.pojo.param.dept.DeptParam;
import com.cy.sys.service.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * 组织架构模块
 * @author CY
 * @since 2020-11-24
 */
@RestController
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Resource
    private ISysDeptService sysDeptService1;

    /**
     * 保存部门
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    public ApiResp save(@RequestBody @Valid DeptParam param) throws Exception {

        if (Objects.nonNull(param.getId()) && Objects.nonNull(param.getSurrogateId())) {// update
            return sysDeptService1.edit(param);
        }else { // insert
            return sysDeptService1.add(param);
        }
    }

    /**
     * 新增部门信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public ApiResp add(@RequestBody @Valid DeptParam param) throws Exception {
        return sysDeptService1.add(param);
    }

    /**
     * 修改部门信息
     * 支持修改部门层级
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("edit")
    public ApiResp edit(@RequestBody @Validated({DeptParam.GroupEdit.class}) DeptParam param) throws Exception {
        return sysDeptService1.edit(param);
    }

    /**
     * 获取组织结构树结构
     * @return
     */
    @PostMapping("deptTreeList")
    public ApiResp deptTreeList() throws Exception {
        return sysDeptService1.deptTree();
    }

    /**
     * 获取组织结构树结构
     * @return
     */
    @PostMapping("deptListPage")
    public ApiResp deptListPage(@RequestBody @Validated({DeptListAllParam.GroupPage.class}) DeptListAllParam param) throws Exception {
        return sysDeptService1.deptListPage(param);
    }

    /**
     * 获取所有部门信息
     * @return
     * @throws Exception
     */
    @PostMapping("deptListAll")
    public ApiResp deptListAll(@RequestBody @Valid DeptListAllParam param) throws Exception {
        return sysDeptService1.deptListAll(param);
    }

    /**
     * 获取当前部门的子部门信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("getChildrenDeptList")
    public ApiResp getChildrenDeptList(@RequestBody @Valid DeptGetChildrenParam param) throws Exception {
        return sysDeptService1.getChildrenDeptList(param);
    }

    /**
     * 删除部门
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("delete")
    public ApiResp delete(@RequestBody @Valid DeptDeleteParam param) throws Exception {
        return sysDeptService1.delete(param);
    }

}

