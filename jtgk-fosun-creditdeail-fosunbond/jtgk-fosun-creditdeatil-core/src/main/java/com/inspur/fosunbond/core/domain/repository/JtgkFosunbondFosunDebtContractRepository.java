package com.inspur.fosunbond.core.domain.repository;



import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondFosunDebtContractEntity;
import io.iec.edp.caf.data.orm.DataRepository;

public interface JtgkFosunbondFosunDebtContractRepository extends DataRepository<JtgkFosunbondFosunDebtContractEntity,String> {
    JtgkFosunbondFosunDebtContractEntity findBySourceid(String sourceid);
}
