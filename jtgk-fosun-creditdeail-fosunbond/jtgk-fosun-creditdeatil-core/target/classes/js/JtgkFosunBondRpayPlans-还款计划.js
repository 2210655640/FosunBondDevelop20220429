idp.event.bind("domReady",function(){
    idp.event.register("grid_194263", "beforeGridFilter", function (e, filter) {
      
        debugger;
    
        //获取债券代码
        let windcode =  idp.utils.getQuery("windcode"); 

        if (windcode != "" && windcode != null) {
            filter.push({
                "Left": "",
                "Field": "FOSUNBONDRPAYTPLANS.WINDCODE",
                "Operate": "=",
                "IsExpress": false,
                "Value": windcode,
                "Right": "",
                "Logic": "  "
            });
        }
        return filter;
    });

})
idp.event.bind("viewReady",function(e,context){
    //menu.query();
    $("#grid_194263").leeGrid({
        onUnSelectRow: onunSelectRow
    });
    function onunSelectRow(row) {
        debugger;
      row["CASH_FLOWS_PER_CNY100PAR"]=row["ACCRUED_PRINCIPAL_PER_CNY100PAR"]+row["ACCRUED_INTEREST_PER_CNY100PAR"];
      //idp.control.get("grid_194263").updateCell("CASH_FLOWS_PER_CNY100PAR", row["CASH_FLOWS_PER_CNY100PAR"], row.__index);


    }
})

let menu={
    saveandupdate:function(){
        debugger;
        var windcode= idp.utils.getQuery("windcode");
        let grid=idp.control.get("grid_194263");
        //let data=grid.getData();
        let rows=grid.rows;
        var resetrows=[];
        if(rows.length>0)
        {
            $.each(rows,function(index,item){
                if(item.__status=="update")
                {  
                    if(item.WINDCODE==null||item.WINDCODE==""||item.WINDCODE=="")
                    {
                        item.WINDCODE=windcode;
                    }
                    if(item.STATSUS==null||item.STATSUS==""||item.STATSUS=="")
                    {
                        item.STATSUS="0";
                    }
                    if(item.CREATEDTIME==null||item.CREATEDTIME==""||item.CREATEDTIME=="")
                    {
                        item.CREATEDTIME=menu.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss");
                    }
                    if(item.CREATOR==null||item.CREATOR==""||item.CREATOR=="")
                    {
                        item.CREATOR=idp.context.get("UserId");
                    }
                    if(item.LASTMODIFIER==null||item.LASTMODIFIER==""||item.LASTMODIFIER=="")
                    {
                        item.LASTMODIFIER=idp.context.get("UserId");
                    }
                    if(item.LASTMODIFIEDTIME==null||item.LASTMODIFIEDTIME==""||item.LASTMODIFIEDTIME=="")
                    {
                        item.LASTMODIFIEDTIME=menu.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss");
                    }
                
                    resetrows.push(item);
                }
            })
        }
        if(resetrows.length>0)
        {
            idp.service.fetch("/api/jtgk/fosunbond/v1.0/getfsun/savefosunbondrpayplans",{fosunbondrpayplansentity:JSON.stringify(resetrows),windcode:windcode},false).done(function(data1){
                if(data1.success)
                {
                    var fosunbondrpayplans=data1.result;
                    menu.resetDataAndReload(fosunbondrpayplans);
                }
                else
                {
                    idp.warn(data1.message);
                }
            })
        
        }

    },
    delete:function(){
        debugger;
        var windcode= idp.utils.getQuery("windcode");
        let grid=idp.control.get("grid_194263");
        let data=grid.selected;
        if(data.length==0)
        {
            idp.warn("请选中删除行");
            return false;
        }
        idp.confirm("确定要删除吗?",function(){
            var deleteStrs=data.ID;
            idp.service.fetch("/api/jtgk/fosunbond/v1.0/getfsun/deletefosunbondrpayplansbyid",
            {deleteids:deleteStrs},false).done(function(data1){
               if(data1.success)
               {
                   var fosunbondrpayplans=data1.result;
                   menu.resetDataAndReload(fosunbondrpayplans);
                  
               }
               else
               {
                   idp.warn(data1.message);
               }            
            })

        },function(){
            return false;
        })
   

    },
    query:function(){
        debugger;
        var windcode=idp.utils.getQuery("windcode");
        idp.service.fetch("/api/jtgk/fosunbond/v1.0/getfsun/getfosunbondrpayplansbywindcode",
        {windcode:windcode},false).done(function(data1){
           if(data1.success)
           {
               var fosunbondrpayplans=data1.result;
               menu.resetDataAndReload(fosunbondrpayplans);
              
           }
           else
           {
               idp.warn(data1.message);
           }            
        })
  

    },
    resetDataAndReload:function(fosunbondrpayplans){
        for(var i=0;i<data.length;i++)
        {
           if(fosunbondrpayplans[i].ZJACCRUED_INTEREST_PER_CNY100PAR)
           {
             fosunbondrpayplans[i].ZJACCRUED_INTEREST_PER_CNY100PAR=fosunbondrpayplans[i].ACCRUED_INTEREST_PER_CNY100PAR;

           }
           if(fosunbondrpayplans[i].ZJCASH_FLOWS_DATE)
           {
             fosunbondrpayplans[i].ZJCASH_FLOWS_DATE=fosunbondrpayplans[i].CASH_FLOWS_DATE;

           }
           if(fosunbondrpayplans[i].ZJACCRUED_PRINCIPAL_PER_CNY100PAR)
           {
             fosunbondrpayplans[i].ZJACCRUED_PRINCIPAL_PER_CNY100PAR=fosunbondrpayplans[i].ACCRUED_PRINCIPAL_PER_CNY100PAR;

           }
           if(fosunbondrpayplans[i].ZJCASH_FLOWS_PER_CNY100PAR)
           {
             fosunbondrpayplans[i].ZJCASH_FLOWS_PER_CNY100PAR=fosunbondrpayplans[i].CASH_FLOWS_PER_CNY100PAR;

           }

        }
        idp.control.get("grid_194263").loadData({Rows:fosunbondrpayplans});
       
    },
    dateFormat: function (date, fmt) {
        let o = {
            "M+": date.getMonth() + 1, //月份
            "d+": date.getDate(), //日
            "H+": date.getHours(), //小时
            "m+": date.getMinutes(), //分
            "s+": date.getSeconds(), //秒
            "q+": Math.floor((date.getMonth() + 3) / 3), //季度
            "S": date.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (let k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
}