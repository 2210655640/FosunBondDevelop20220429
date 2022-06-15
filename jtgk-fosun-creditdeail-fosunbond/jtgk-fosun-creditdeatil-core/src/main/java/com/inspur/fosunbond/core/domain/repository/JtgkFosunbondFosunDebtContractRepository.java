package com.inspur.fosunbond.core.domain.repository;



import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondFosunDebtContractEntity;
import io.iec.edp.caf.data.orm.DataRepository;

import java.util.Date;
import java.util.List;

public interface JtgkFosunbondFosunDebtContractRepository extends DataRepository<JtgkFosunbondFosunDebtContractEntity,String> {
    JtgkFosunbondFosunDebtContractEntity findBySourceid(String sourceid);
    List<JtgkFosunbondFosunDebtContractEntity> findAllByWindcodeAndSdateBeforeOrderBySdateDesc(String windcode, Date sdate);
}
