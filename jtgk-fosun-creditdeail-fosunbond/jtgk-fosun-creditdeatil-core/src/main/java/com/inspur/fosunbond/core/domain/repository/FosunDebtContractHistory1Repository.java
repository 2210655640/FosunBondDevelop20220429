package com.inspur.fosunbond.core.domain.repository;


import com.inspur.fosunbond.core.domain.entity.FosunDebtContractHistory1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface FosunDebtContractHistory1Repository extends JpaRepository<FosunDebtContractHistory1Entity,String>, JpaSpecificationExecutor<FosunDebtContractHistory1Entity> {
   List<FosunDebtContractHistory1Entity> findAllByHistoryversiondate(Date versiondate);
   boolean existsByIdAndHistoryversiondate(String id,Date hisdate);
   void deleteByIdAndHistoryversiondate(String id,Date hisdate);
   List<FosunDebtContractHistory1Entity>  findAllBySourceidOrderByHistoryversiondateDesc(String sourceid);
}
