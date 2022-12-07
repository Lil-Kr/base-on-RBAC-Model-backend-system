package com.cy.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.entity.SysDept;
import com.cy.sys.pojo.param.dept.DeptDeleteParam;
import com.cy.sys.pojo.param.dept.DeptGetChildrenParam;
import com.cy.sys.pojo.param.dept.DeptListAllParam;
import com.cy.sys.pojo.param.dept.DeptParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CY
 * @since 2020-11-24
 */
public interface ISysDeptService extends IService<SysDept> {

    ApiResp add(DeptParam param) throws Exception;

    ApiResp edit(DeptParam param) throws Exception;

    ApiResp getChildrenDeptList(DeptGetChildrenParam dto) throws Exception;

    ApiResp deptTree() throws Exception;

    ApiResp delete(DeptDeleteParam dto) throws Exception;

    ApiResp deptListAll(DeptListAllParam param) throws Exception;

    ApiResp deptListPage(DeptListAllParam param) throws Exception;
}
