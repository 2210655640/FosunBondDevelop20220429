package com.inspur.fosunbond.core.domain.repository;



import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondT_IdenticalissUerEntity;
import io.iec.edp.caf.data.orm.DataRepository;

import java.util.List;

public interface JtgkFosunbondT_IdenticalissUerRepository extends DataRepository<JtgkFosunbondT_IdenticalissUerEntity,String> {
 List<JtgkFosunbondT_IdenticalissUerEntity> findAllByWindcode(String windcode);
}
