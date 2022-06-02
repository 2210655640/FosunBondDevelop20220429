package com.inspur.fosunbond.core.domain.repository;




import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondFosunIdenticalissUerEntity;
import io.iec.edp.caf.data.orm.DataRepository;

public interface JtgkFosunbondFosunIdenticalissUerRepository extends DataRepository<JtgkFosunbondFosunIdenticalissUerEntity,String> {
    JtgkFosunbondFosunIdenticalissUerEntity findBySourceid(String sourceid);
}
