package com.cy.sys.pojo.vo.dept;

import com.cy.sys.pojo.entity.SysDept;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

/**
 * @author CY
 * @since 2020-11-24
 */
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysDeptVO extends SysDept {

}
