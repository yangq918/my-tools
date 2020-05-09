package org.yq.tool.yktools.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.yq.tool.yktools.entity.HCHisDoctorEntity;
import org.yq.tool.yktools.entity.HCHisDoctorId;

/**
 * @Auther: QiaoYang
 * @Date: 2020/5/9 17:49
 * @Description:
 */
public interface HCHisDoctorRepository extends PagingAndSortingRepository<HCHisDoctorEntity, HCHisDoctorId> {

}
