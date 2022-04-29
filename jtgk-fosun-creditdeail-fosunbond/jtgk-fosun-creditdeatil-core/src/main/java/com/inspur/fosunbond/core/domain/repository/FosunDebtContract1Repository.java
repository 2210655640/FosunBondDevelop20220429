package com.inspur.fosunbond.core.domain.repository;


import com.inspur.fosunbond.core.domain.entity.FosunDebtContract1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FosunDebtContract1Repository extends JpaRepository<FosunDebtContract1Entity,String>, JpaSpecificationExecutor<FosunDebtContract1Entity> {
    //List<FosunDebtContractEntity> findAllByComp_nameAndBondtypeAndCarrydateAndMaturitydate(String comname,String bondtype,String carrydate,String maturitydate);
}
