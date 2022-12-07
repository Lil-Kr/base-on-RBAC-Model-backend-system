package com.cy.sys.pojo.param.dept;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class DeptGetChildrenParam {

    @NotNull(message = "部门surrogateId不能为空")
    private Long surrogateId;

}
