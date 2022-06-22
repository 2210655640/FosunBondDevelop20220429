package com.inspur.fosunbond.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="FOSUNCOMPANYSORT")
public class JtgkFosunbondFosunCompanySortEntity implements Serializable {
    @Id
    private String id;
    /*
    简称
     */
    private String shortname;
    /*
    排序号
     */
    private String sortnum;

//    private String getSortNum()
//    {
//        if (sortnum==""||sortnum==null)
//        {
//            return "9999";
//        }
//        return sortnum;
//    }

}
