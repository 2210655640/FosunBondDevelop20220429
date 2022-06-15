package com.inspur.fosunbond.core.domain.repository;

import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondT_Debt_ContractEntity;
import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondT_baserateEntity;
import io.iec.edp.caf.data.orm.DataRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JtgkFosunbondT_baserateRepository extends DataRepository<JtgkFosunbondT_baserateEntity,String> {
    @Query(value="select * from t_baserate  where DATE(updatetime)=?1 and code=?2 order by sdate desc ",nativeQuery=true)
    List<JtgkFosunbondT_baserateEntity> getdatabyupdatetime(String updatetime,String code);
}
