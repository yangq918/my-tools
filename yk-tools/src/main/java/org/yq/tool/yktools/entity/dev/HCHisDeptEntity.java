package org.yq.tool.yktools.entity.dev;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name="t_hc_his_dept")
@Entity
public class HCHisDeptEntity {

    @EmbeddedId
    private HCHisDeptKey deptKey;

    @Column(name="his_cid")
    private Long hisCid;

    private String name;

    private String summary;

    private String pid;

    private String address;

    private String tel;

    @Column(name ="sort_no")
    private Integer sortNo;

    private Integer level;

    private String img;

    private String skill;

    @Transient
    private List<HCHisDoctorEntity> doctors;


}
