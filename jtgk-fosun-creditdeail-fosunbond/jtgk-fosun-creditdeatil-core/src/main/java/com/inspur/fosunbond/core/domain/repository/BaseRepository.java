package com.inspur.fosunbond.core.domain.repository;

import com.alibaba.fastjson.JSONArray;
import io.iec.edp.caf.common.JSONSerializer;
import io.iec.edp.caf.commons.utils.CollectionUtils;
import io.iec.edp.caf.commons.utils.SpringBeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作基类
 * 
 * @author zhaoyueming
 * @version 1.0
 * @date 2021/9/15
 */
public class BaseRepository {
    @PersistenceContext
    //private EntityManager manager;
    EntityManager manager = (EntityManager) SpringBeanUtils.getBean(EntityManager.class);

    /**
     * 查询单个对象
     *
     * @param sql sql语句
     * @param clazz 返回对象类型
     * @param param 参数
     * @return 查询结果
     * @author zhaoyueminmg
     * @date 2021/9/15
     */
    private   <T> T queryOne(String sql, Class<T> clazz, String... param) {
        // 查单个getSingleResult没有数据会抛异常，还是用list
        List<T> resultList = queryList(sql, clazz, param);

        T result = null;

        if (!CollectionUtils.isEmpty(resultList)) {
            result = resultList.get(0);
        }

        return result;
    }

    /**
     * 查询列表
     *
     * @param sql sql语句
     * @param clazz 返回对象类型
     * @param param 参数
     * @return 查询结果
     * @author zhaoyueminmg
     * @date 2021/9/15
     */
    public <T> List<T> queryList(String sql, Class<T> clazz, String... param) {
        Query nativeQuery = createNativeQuery(sql, param);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<Object> resultList = nativeQuery.getResultList();

        List<T> result = null;

        if (!CollectionUtils.isEmpty(resultList)) {
            result = JSONArray.parseArray(JSONSerializer.serialize(resultList), clazz);
        }

        return result;
    }

    /**
     * 查询单个字段值
     *
     * @param sql sql语句
     * @param column 字段名（大写）
     * @param param 参数
     * @return 字段值
     * @author zhaoyueminmg
     * @date 2021/9/15
     */
    public String queryString(String sql, String column, String... param) {
        Query nativeQuery = createNativeQuery(sql, param);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        // 查单个getSingleResult没有数据会抛异常，还是用list
        List<Map<String, Object>> resultList = nativeQuery.getResultList();

        String result = null;

        if (!CollectionUtils.isEmpty(resultList)) {
            Object columnValue = resultList.get(0).get(column);

            if (null != columnValue) {
                result = String.valueOf(columnValue);
            }
        }

        return result;
    }

    /**
     * 新增数据
     * 
     * @param sql sql语句
     * @param obj 数据对象
     * @author zhaoyueminmg
     * @date 2021/9/22
     */
    public <T> void insertOne(String sql, Object obj, String... param) {
        if (StringUtils.isNotBlank(sql)) {
            Query nativeQuery = createNativeQuery(sql, param);

            nativeQuery.executeUpdate();
        } else {
            manager.persist(obj);
        }
    }

    /**
     * 批量新增数据
     *
     * @param list 数据集合
     * @author zhaoyueminmg
     * @date 2021/9/22
     */
    public <T> void batchInsert(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            manager.persist(list.get(i));

            if (i % 50 == 0) {
                manager.flush();
                manager.clear();
            }
        }
    }

    /**
     * 更新数据
     * 
     * @param sql sql语句
     * @param obj 数据对象
     * @author zhaoyueminmg
     * @date 2021/9/22
     */
    public void updateOne(String sql, Object obj, String... param) {
        if (StringUtils.isNotBlank(sql)) {
            Query nativeQuery = createNativeQuery(sql, param);

            nativeQuery.executeUpdate();
        } else {
            manager.merge(obj);
        }
    }

    /**
     * 批量更新数据
     *
     * @param list 数据集合
     * @author zhaoyueminmg
     * @date 2021/9/22
     */
    public <T> void batchUpdate(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            manager.merge(list.get(i));

            if (i % 50 == 0) {
                manager.flush();
                manager.clear();
            }
        }
    }

    /**
     * 创建本地查询对象并设置参数
     *
     * @param sql sql语句
     * @param param 参数
     * @return 本地查询对象
     * @author zhaoyueminmg
     * @date 2021/9/28
     */
    private Query createNativeQuery(String sql, String[] param) {
        Query nativeQuery = manager.createNativeQuery(sql);

        // 设置参数
        if (ArrayUtils.isNotEmpty(param)) {
            for (int i = 0; i < param.length; i++) {
                nativeQuery.setParameter(i + 1, param[i]);
            }
        }

        return nativeQuery;
    }
}
