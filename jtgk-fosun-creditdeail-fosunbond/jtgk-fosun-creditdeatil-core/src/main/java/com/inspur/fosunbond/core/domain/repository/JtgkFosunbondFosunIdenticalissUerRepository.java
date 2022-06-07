package com.inspur.fosunbond.core.domain.repository;




import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondFosunIdenticalissUerEntity;
import io.iec.edp.caf.data.orm.DataRepository;

import java.util.Date;
import java.util.List;

public interface JtgkFosunbondFosunIdenticalissUerRepository extends DataRepository<JtgkFosunbondFosunIdenticalissUerEntity,String> {
    JtgkFosunbondFosunIdenticalissUerEntity findBySourceid(String sourceid);
    List<JtgkFosunbondFosunIdenticalissUerEntity> findAllByWindcodeOrderByIssuedateDesc(String windcode);

    List<JtgkFosunbondFosunIdenticalissUerEntity> findAllByWindcodeAndIssuedateBeforeOrderByIssuedateDesc(String windcode, Date issuedate);
}
