package com.inspur.fosunbond.core.domain.repository;


import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondT_Debt_ContractEntity;
import io.iec.edp.caf.data.orm.DataRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface JtgkFosunbondT_Debt_ContractRepository extends DataRepository<JtgkFosunbondT_Debt_ContractEntity,String> {
    List<JtgkFosunbondT_Debt_ContractEntity> findAllByFullnameIsNotNull();
    List<JtgkFosunbondT_Debt_ContractEntity> findAllByUpdatetimeContains(Date updatetime);
    List<JtgkFosunbondT_Debt_ContractEntity> findAllByUpdatetime(Date updatetime);
    @Query(value="select * from v_debt_contract  where DATE(updatetime)=?1 ",nativeQuery=true)
    List<JtgkFosunbondT_Debt_ContractEntity> getdatabyupdatetime(String updatetime);


}
