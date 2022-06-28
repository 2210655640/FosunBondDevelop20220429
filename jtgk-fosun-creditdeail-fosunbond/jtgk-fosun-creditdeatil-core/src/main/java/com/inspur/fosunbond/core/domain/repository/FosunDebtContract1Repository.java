package com.inspur.fosunbond.core.domain.repository;


import com.inspur.fosunbond.core.domain.entity.FosunDebtContract1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface FosunDebtContract1Repository extends JpaRepository<FosunDebtContract1Entity,String>, JpaSpecificationExecutor<FosunDebtContract1Entity> {
    //List<FosunDebtContractEntity> findAllByComp_nameAndBondtypeAndCarrydateAndMaturitydate(String comname,String bondtype,String carrydate,String maturitydate);
    @Modifying
    @Query(value="update fosundebtcontract set originalrate=?1  where windcode=?2",nativeQuery=true)
    int updateDataByID(BigDecimal originalrate,String windcode);
}
