package com.inspur.fosunbond.core.domain.repository;

import com.inspur.fosunbond.core.domain.entity.T_debt_cashflowEntity;
import io.iec.edp.caf.data.orm.DataRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface T_debt_cashflowRepository extends DataRepository<T_debt_cashflowEntity,String> {
    @Query(value="select * from t_debt_cashflow  where DATE(createtime)=?1 ",nativeQuery=true)
    List<T_debt_cashflowEntity> getdatabycreatetime(String createtime);
}
