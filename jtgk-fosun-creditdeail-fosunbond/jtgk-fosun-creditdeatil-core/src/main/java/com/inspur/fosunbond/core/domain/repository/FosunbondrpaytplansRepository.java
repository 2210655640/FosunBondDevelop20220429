package com.inspur.fosunbond.core.domain.repository;

import com.inspur.fosunbond.core.domain.entity.FosunbondrpaytplansEntity;
import io.iec.edp.caf.data.orm.DataRepository;

import java.util.List;

public interface FosunbondrpaytplansRepository extends DataRepository<FosunbondrpaytplansEntity,String> {
    List<FosunbondrpaytplansEntity> findAllByWindcodeAndDelflagNotOrderByCreatedtimeAsc(String windcode,String delflag);
    List<FosunbondrpaytplansEntity> findAllBySourceid(String sourceid);
    List<FosunbondrpaytplansEntity> findAllByWindcodeAndDelflagNotOrderByCashflowsdateAsc(String windcode,String delflag);
    List<FosunbondrpaytplansEntity> findAllByWindcodeAndDelflagNotOrderByZjcashflowsdateAsc(String windcode,String delflag);
}
