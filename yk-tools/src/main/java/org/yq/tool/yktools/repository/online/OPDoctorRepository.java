package org.yq.tool.yktools.repository.online;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yq.tool.yktools.entity.online.OPHisDoctorEntity;

public interface OPDoctorRepository extends JpaRepository<OPHisDoctorEntity,Long> {
}
