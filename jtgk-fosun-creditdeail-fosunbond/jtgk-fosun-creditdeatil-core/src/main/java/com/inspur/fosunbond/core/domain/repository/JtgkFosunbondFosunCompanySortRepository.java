package com.inspur.fosunbond.core.domain.repository;

import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondFosunCompanySortEntity;
import io.iec.edp.caf.data.orm.DataRepository;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface JtgkFosunbondFosunCompanySortRepository extends DataRepository<JtgkFosunbondFosunCompanySortEntity,String> {
    List<JtgkFosunbondFosunCompanySortEntity> findAllByShortname(String shortname);
}
