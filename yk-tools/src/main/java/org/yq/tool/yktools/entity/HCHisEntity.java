package org.yq.tool.yktools.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="t_hc_his")
@Entity
public class HCHisEntity  {
    @Id
    private Long id;

    private String name;


    private String address;
}
