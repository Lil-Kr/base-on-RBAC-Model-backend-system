package com.cy.sys.pojo.dto.dept;

import com.cy.sys.pojo.entity.SysDept;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@ToString
public class DeptLevelDto extends SysDept {

    private List<DeptLevelDto> deptList = Lists.newArrayList();

    /**
     * 将部门数据转换为一颗树形结构
     * @param dept
     * @return
     */
    public static DeptLevelDto adapt(SysDept dept){
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept,dto);
        return dto;
    }


}
