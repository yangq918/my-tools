package org.yq.tool.yktools.entity.online;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="t_dc_his")
@Entity
public class OPHisEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "his_id")
    private Long hisId;

    private String name;

    private String province;


    private String city;

    @Column(name = "his_logo")
    private String hisLogo;

    private Integer origin;
}
