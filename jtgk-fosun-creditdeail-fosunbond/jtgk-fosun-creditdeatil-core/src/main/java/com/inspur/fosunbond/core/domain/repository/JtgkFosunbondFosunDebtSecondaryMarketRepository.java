package com.inspur.fosunbond.core.domain.repository;




import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondFosunDebtSecondaryMarketEntity;
import io.iec.edp.caf.data.orm.DataRepository;

public interface JtgkFosunbondFosunDebtSecondaryMarketRepository extends DataRepository<JtgkFosunbondFosunDebtSecondaryMarketEntity,String> {
        JtgkFosunbondFosunDebtSecondaryMarketEntity findBySourceid(String sourceid);
}
