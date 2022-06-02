package com.inspur.fosunbond.core.domain.repository;



import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondT_Debt_SecondaryMarketEntity;
import io.iec.edp.caf.data.orm.DataRepository;

import java.util.List;

public interface JtgkFosunbondT_Debt_SecondaryMarketRepository extends DataRepository<JtgkFosunbondT_Debt_SecondaryMarketEntity,String> {
    List<JtgkFosunbondT_Debt_SecondaryMarketEntity> findAllByWindcode(String wincode);
}
