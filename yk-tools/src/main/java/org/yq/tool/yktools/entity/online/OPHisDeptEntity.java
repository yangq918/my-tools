package org.yq.tool.yktools.entity.online;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="t_dc_dept")
@Entity
public class OPHisDeptEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long his_id;

    private String dept_no;

    private String name;
}
