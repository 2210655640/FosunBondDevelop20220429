package com.inspur.fosunbond.core.domain.repository;


import com.inspur.fosunbond.core.domain.entity.FosunDebtContractHistory1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface FosunDebtContractHistory1Repository extends JpaRepository<FosunDebtContractHistory1Entity,String>, JpaSpecificationExecutor<FosunDebtContractHistory1Entity> {
   List<FosunDebtContractHistory1Entity> findAllByHistoryversiondate(Date versiondate);
   boolean existsByIdAndHistoryversiondate(String id,Date hisdate);
   void deleteByIdAndHistoryversiondate(String id,Date hisdate);
   List<FosunDebtContractHistory1Entity>  findAllBySourceidOrderByHistoryversiondateDesc(String sourceid);
   @Modifying
   @Query(value="update fosundebtcontracthistory set originalrate=?1  where windcode=?2",nativeQuery=true)
   int updateDataByID(BigDecimal originalrate, String windcode);
}
