package com.inspur.fosunbond.core.domain.repository;


import com.google.common.base.Joiner;
import io.iec.edp.caf.commons.transaction.JpaTransaction;
import io.iec.edp.caf.commons.transaction.TransactionPropagation;
import io.iec.edp.caf.commons.utils.SpringBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@Slf4j
public class JtgkFosunBondNativeQueryRepo {

    public static String createFatherSubQuery(String father, String sub) {
        father = father.trim();
        sub = sub.trim();

        return "SELECT ${return} FROM " + father + " f LEFT JOIN " + sub + " s ON s.parentid = f.id";
    }

    public static String countSql(String sql) {
        return sql.replace("${return}", "count(*) as count");
    }

    public static String fillReturn(String sql, List<String> f, List<String> s) {

        if (s.size() == 0 && f.size() == 0) {
            throw new RuntimeException();
        }

        Set<String> set = new HashSet<>();
        for (String s1 : f) {
            s1 = s1.trim();
            set.add("f." + s1 + " as " + "f_" + s1);
        }
        for (String s1 : s) {
            s1 = s1.trim();
            set.add("s." + s1 + " as " + "s_" + s1);
        }

        return sql.replace("${return}", Joiner.on(",").join(set));
    }


    public static List<Map<String, Object>> queryList(String sql) {
        EntityManager manager = SpringBeanUtils.getBean(EntityManager.class);
        Query nativeQuery = manager.createNativeQuery(sql);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> resultList = nativeQuery.getResultList();
        for (Map<String, Object> singleResult : resultList) {
            for (String s : new HashSet<>(singleResult.keySet())) {
                String lowerCase = s.toLowerCase();
                if (!singleResult.containsKey(lowerCase)) {
                    singleResult.put(lowerCase, singleResult.get(s));
                }
            }
        }
        return resultList;
    }

    public static Map<String, Object> queryOne(String sql) {
        EntityManager manager = SpringBeanUtils.getBean(EntityManager.class);
        Query nativeQuery = manager.createNativeQuery(sql);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        try {
            Map<String, Object> singleResult = (Map<String, Object>) nativeQuery.getSingleResult();

            for (Map.Entry<String, Object> entry : new HashSet<>(singleResult.entrySet())) {
                String key = entry.getKey();
                Object value = entry.getValue();
                //值变换
                if (value instanceof Number) {
                    double v = ((Number) value).doubleValue();
                    singleResult.put(key, v);
                }
                String lowerCase = key.toLowerCase();
                if (!singleResult.containsKey(lowerCase)) {
                    singleResult.put(lowerCase, singleResult.get(key));
                }
            }
            return singleResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }


    public static int execute(String sql) {
        JpaTransaction tran = JpaTransaction.getTransaction();
        int res = 0;
        try {
            //数据插入和更新需要启用数据库事务executeUpdate
            tran.begin(TransactionPropagation.REQUIRES_NEW);
            EntityManager entityManager = SpringBeanUtils.getBean(EntityManager.class);
            Query query = entityManager.createNativeQuery(sql);
            res = query.executeUpdate();
            log.info("deleteFromHistoryLog执行完成！");
            tran.commit();
        } catch (Exception ex) {
            tran.rollback();
            log.error("deleteFromHistoryLog执行异常：", ex);
        }
        return res;
       /* EntityManagerFactory factory = SpringBeanUtils.getBean(EntityManagerFactory.class);

        EntityManager manager = factory.createEntityManager();
        Query nativeQuery = manager.createNativeQuery(sql);
        int r=0;
        try {
            manager.getTransaction().begin();
            r = nativeQuery.executeUpdate();
            manager.getTransaction().commit();
        }catch (Exception e){
            System.out.print("执行sql出错"+sql+"\n"+e.getMessage());
        }finally {
            manager.getTransaction().rollback();
        }
        return r;*/
    }


}

