package com.inspur.fosunbond.core.domain.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inspur.fosunbond.core.domain.repository.NativeQueryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyc
 */
@Service
@Slf4j
public class Creditdeatil111Service {
    public String updateCreditDetail(String disDataStr) {

        try {
            log.info("updateCreditDetail:" +disDataStr );
            JSONArray jsonArr = JSONArray.parseArray(disDataStr);
            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject job = jsonArr.getJSONObject(i);
                String id = job.get("id").toString();
                String hxfs = job.get("hxfs") == null ? "" : job.get("hxfs").toString();
                String bz = job.get("bz") == null ? "" : job.get("bz").toString();
                String ywlx = job.get("ywlx") == null ? "" : job.get("ywlx").toString();
                if (ywlx.equals(("4"))) {
                    //提款
                    String upsql = "update TMBANKLOANIOUS set TXT01='" + hxfs + "' ,TXT02= '" + bz + "' where id = '" + id + "'";
                    log.info("提款sql:" +upsql );
                    NativeQueryRepo.execute(upsql);
                }
                else if (ywlx.equals("5")||ywlx.equals("6"))//还款计划还本或付息
                {
                    String upsql="update fosunbondrpaytplans set remarks='"+bz+"' where id='"+id+"'";
                    log.info("还款计划sql:"+upsql);
                    NativeQueryRepo.execute(upsql);
                }
                else {
                    //还本付息
                    String upsql = "update TMBANKLOANIOURPAYTPLANS set TXT01='" + hxfs + "' ,TXT02= '" + bz + "' where id = '" + id + "'";
                    log.error("还本付息sql:" +upsql );
                    NativeQueryRepo.execute(upsql);
                }
            }
            JSONObject jo = new JSONObject();
            jo.put("code","0");
            jo.put("msg","");
            return jo.toString();
        }catch (Exception ex ){
            JSONObject jo = new JSONObject();
            jo.put("code","1");
            jo.put("msg",ex.toString());
            return jo.toString();
        }
    }
}
