package com.cy.sys.pojo.dto;

import com.cy.sys.pojo.entity.SysDept;
import com.cy.sys.pojo.param.dept.DeptParam;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
@Data
@ToString
public class DeptDto extends SysDept{

    /**
     * 参数转换为实体类
     * @param param
     * @return
     */
    public static SysDept paramToSysDept(DeptParam param) {
        SysDept dept = SysDept.builder().build();
        BeanUtils.copyProperties(param,dept);
        return dept;
    }

    public static SysDept paramToSysDept(SysDept param) {
        SysDept dept = SysDept.builder().build();
        BeanUtils.copyProperties(param,dept);
        return dept;
    }
}
