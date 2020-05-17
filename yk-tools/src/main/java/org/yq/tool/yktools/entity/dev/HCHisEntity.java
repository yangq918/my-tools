package org.yq.tool.yktools.entity.dev;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name="t_hc_his")
@Entity
public class HCHisEntity  {
    @Id
    private Long id;

    private String name;


    private String address;

    @Column(name="tel_no")
    private String telNo;

    @Column(name="logo_url")
    private String logoUrl;

    @Transient
    private List<HCHisDeptEntity> depts;

}
