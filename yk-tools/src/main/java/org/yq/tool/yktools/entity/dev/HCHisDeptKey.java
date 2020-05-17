package org.yq.tool.yktools.entity.dev;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@Data
public class HCHisDeptKey implements Serializable {

    private static final long serialVersionUID = -8986993035667872803L;

    @Column(name = "his_id")
    private Long hisId;

    private String no;

}
