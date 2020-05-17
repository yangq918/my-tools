package org.yq.tool.yktools.repository.online;

import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yq.tool.yktools.entity.online.OPHisEntity;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface OPHisRepository extends JpaRepository<OPHisEntity,Long> {
    OPHisEntity findByName(String name);

    @Query("select max(p.hisId) from OPHisEntity p")
    Long findMaxHisId();
}
