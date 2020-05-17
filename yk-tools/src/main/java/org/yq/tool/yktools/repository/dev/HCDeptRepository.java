package org.yq.tool.yktools.repository.dev;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yq.tool.yktools.entity.dev.HCHisDeptEntity;
import org.yq.tool.yktools.entity.dev.HCHisDeptKey;

public interface HCDeptRepository extends JpaRepository<HCHisDeptEntity, HCHisDeptKey> {
}
