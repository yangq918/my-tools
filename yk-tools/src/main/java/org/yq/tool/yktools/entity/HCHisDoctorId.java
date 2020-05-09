package org.yq.tool.yktools.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

/**
 * @Auther: QiaoYang
 * @Date: 2020/5/9 18:03
 * @Description:
 */
@Data
public class HCHisDoctorId {

    @Column("his_id")
    private Long hisId;

    private String no;

    @Column("dept_no")
    private String deptNo;
}
