package com.inspur.fosunbond.core.domain.repository;

import com.inspur.fosunbond.core.domain.entity.JtgkFosunBondBfblinterestrateverEntity;
import io.iec.edp.caf.data.orm.DataRepository;

import java.util.List;

public interface JtgkFosunBondBfblinterestrateverRepository extends DataRepository<JtgkFosunBondBfblinterestrateverEntity,String> {
List<JtgkFosunBondBfblinterestrateverEntity> findAllByParentidOrderByStartdateDesc(String parentid);
}
