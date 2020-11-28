package com.cy.sys.pojo.dto.aclmodule;

import com.cy.sys.pojo.entity.SysAclModule;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author CY
 * @since 2020-11-26
 */
@Data
@ToString
@Builder
public class AclModuleDto extends SysAclModule {

    private List<AclModuleDto> aclModuleDtoList = Lists.newArrayList();

    /**
     * 将权限模块数据转换为一颗树形结构
     * @return
     */
    public static AclModuleDto adapt(SysAclModule aclModule){
        AclModuleDto dto = AclModuleDto.builder().build();
        BeanUtils.copyProperties(aclModule,dto);
        return dto;
    }
}
