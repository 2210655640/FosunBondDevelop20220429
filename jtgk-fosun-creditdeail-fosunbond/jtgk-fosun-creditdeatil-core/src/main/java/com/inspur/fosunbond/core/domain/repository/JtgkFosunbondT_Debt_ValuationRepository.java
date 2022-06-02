package com.inspur.fosunbond.core.domain.repository;



import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondT_Debt_ValuationEntity;
import io.iec.edp.caf.data.orm.DataRepository;

import java.util.List;

public interface JtgkFosunbondT_Debt_ValuationRepository extends DataRepository<JtgkFosunbondT_Debt_ValuationEntity,String> {
    List<JtgkFosunbondT_Debt_ValuationEntity> findAllByWindcode(String wincode);
}
