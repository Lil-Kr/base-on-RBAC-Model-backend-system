package com.cy.sys.util.dept;

import com.cy.sys.pojo.dto.DeptLevelDto;
import com.cy.sys.pojo.entity.SysDept;

import java.util.Comparator;

public class DeptUtil {


    /**
     * 以DeptLevelDto排序, 部门列表根据seq排序
     */
    public static Comparator<DeptLevelDto> deptLevelDtoComparator = new Comparator<DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 以SysDept排序
     */
    public static Comparator<SysDept> deptComparator = new Comparator<SysDept>() {
        @Override
        public int compare(SysDept o1, SysDept o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 以SysDept排序
     */
    public static Comparator<SysDept> deptByIdComparator = new Comparator<SysDept>() {
        @Override
        public int compare(SysDept o1, SysDept o2) {
            return (int) (o1.getId() - o2.getId());
        }
    };
}
