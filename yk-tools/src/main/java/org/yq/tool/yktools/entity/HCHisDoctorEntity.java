package org.yq.tool.yktools.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @Auther: QiaoYang
 * @Date: 2020/5/9 17:32
 * @Description:
 */
@Data
@Table("t_hc_his_doctor")
public class HCHisDoctorEntity {


    @Id
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
    private HCHisDoctorId hcHisDoctorId;



    @Column("platform_id")
    private Long platformId;




    private String name;

    private String sex;


    private String title;

    private String img;


    private String skill;

    private String level;
}
