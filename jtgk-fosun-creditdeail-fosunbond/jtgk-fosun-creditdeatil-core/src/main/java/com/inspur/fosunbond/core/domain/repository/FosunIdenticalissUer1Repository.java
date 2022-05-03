package com.inspur.fosunbond.core.domain.repository;


import com.inspur.fosunbond.core.domain.entity.FosunIdenticalissUer1Entity;
import io.iec.edp.caf.data.orm.DataRepository;

import java.util.List;

public interface FosunIdenticalissUer1Repository extends DataRepository<FosunIdenticalissUer1Entity,String> {

    List<FosunIdenticalissUer1Entity> findAllByWindcode(String windcode);
}
