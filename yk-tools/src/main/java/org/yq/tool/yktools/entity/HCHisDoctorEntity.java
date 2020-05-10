package org.yq.tool.yktools.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Auther: QiaoYang
 * @Date: 2020/5/9 17:32
 * @Description:
 */
@Data
@Table(name="t_hc_his_doctor")
@Entity
public class HCHisDoctorEntity {
    @Id
    @Column(name="id")
   private Long id;


    private String no;

    @Column(name="dept_no")
    private String deptNo;



    @Column(name="platform_id")
    private Long platformId;




    private String name;

    private String sex;


    private String title;

    private String img;


    private String skill;

    private String level;

    @ManyToOne
    @JoinColumn(name="his_id")
    private HCHisEntity his;

}
