package com.inspur.fosunbond.core.domain.repository;

import com.inspur.fosunbond.core.domain.entity.JtgkFosunBondBfblinterestrateEntity;
import io.iec.edp.caf.data.orm.DataRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface JtgkFosunBondBfblinterestrateRepository extends DataRepository<JtgkFosunBondBfblinterestrateEntity,String> {
    List<JtgkFosunBondBfblinterestrateEntity> findAllByIrterm(String irterm);
    @Modifying
    @Query(value="UPDATE bfblinterestrate set annualir=?1,irdate=?2 where id=?3 ",nativeQuery=true)
    int updatebyID(BigDecimal annualir, Date irdate,String id);
}
