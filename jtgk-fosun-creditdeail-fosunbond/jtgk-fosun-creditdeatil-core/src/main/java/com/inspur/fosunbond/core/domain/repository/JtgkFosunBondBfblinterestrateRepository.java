package com.inspur.fosunbond.core.domain.repository;

import com.inspur.fosunbond.core.domain.entity.JtgkFosunBondBfblinterestrateEntity;
import io.iec.edp.caf.data.orm.DataRepository;

import java.util.List;

public interface JtgkFosunBondBfblinterestrateRepository extends DataRepository<JtgkFosunBondBfblinterestrateEntity,String> {
    List<JtgkFosunBondBfblinterestrateEntity> findAllByIrterm(String irterm);
}
