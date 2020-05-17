package org.yq.tool.yktools.entity.online;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="t_dc_doctor")
@Entity
public class OPHisDoctorEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long his_id;

    private String doctor_code;

    private Long dept_id;

    private String name;

    private String mobile;

    private String id_no;

    private String sex;

    private String img_url;

    private String level;

    private String introduction;

    private String specialty;

    private String honor_award;


    private String experience;

    private String achievement;

    private String sentry;

    private String status;

    private String check_status;

    private String origin;
}
