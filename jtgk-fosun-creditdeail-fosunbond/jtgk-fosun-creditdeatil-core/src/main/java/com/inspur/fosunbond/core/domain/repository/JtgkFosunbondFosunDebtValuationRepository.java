package com.inspur.fosunbond.core.domain.repository;



import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondFosunDebtValuationEntity;

import io.iec.edp.caf.data.orm.DataRepository;

public interface JtgkFosunbondFosunDebtValuationRepository extends DataRepository<JtgkFosunbondFosunDebtValuationEntity,String> {
    JtgkFosunbondFosunDebtValuationEntity findBySourceid(String sourceid);
}
