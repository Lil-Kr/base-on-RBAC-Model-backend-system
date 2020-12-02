package com.cy.sys.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author CY
 * @since 2020-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDictDetail extends Model<SysDictDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典id唯一主键
     */
      @TableId(value = "surrogate_id", type = IdType.AUTO)
    private Long surrogateId;

    /**
     * 数据字典主表id
     */
    private Long sysDictId;

    /**
     * 数据字典明细名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.surrogateId;
    }

}
