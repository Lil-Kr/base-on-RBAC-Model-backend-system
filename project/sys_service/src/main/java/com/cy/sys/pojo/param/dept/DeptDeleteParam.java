package com.cy.sys.pojo.param.dept;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class DeptDeleteParam {

    /**
     * 自增主键
     */
    @NotNull(message = "自增id不能为空")
    private Long id;

    /**
     * 部门唯一主键
     */
    @NotNull(message = "部门surrogateId不能为空")
    private Long surrogateId;
}
