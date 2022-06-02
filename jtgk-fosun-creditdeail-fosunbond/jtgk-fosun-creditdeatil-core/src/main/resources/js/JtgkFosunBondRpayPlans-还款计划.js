function openUrl() {
    const url = "/apps/fastdweb/views/runtime/page/query/querypreview.html?styleid=d34c716e-4b98-4c71-1ef1-cd27309685d3";
    idp.utils.openurl('', '周转计划', url);
}

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

    //var content1 = `<img src="/apps/sankey/img/addIcon.svg">&nbsp;&nbsp<span>新增</span>`;
    setTimeout(function() {
        //$(".lee-btn-primary")[0].innerHTML = content1;
        //$(".lee-tab-links").prepend("<div class=backlys> <&nbsp;&nbsp;周转计划</div>")

        $(".lee-tab-links").prepend("<div class=backlys  onclick='openUrl()'><&nbsp;&nbsp;周转计划</div>")

    }, 100);

    idp.event.register("grid_ROBXFK","afterEndEdit",function(e,opts){
        debugger;
        var row=opts.record;//行数据
        var column=opts.column;//列数据
        var val=opts.value; //单元格值
        var index=opts.rowindex; //行索引
     });

})
idp.event.bind("viewReady",function(e,context){
    menu.query();
    $("#grid_194263").leeGrid({
        onBeforeEdit: onbeforeedit
    });
    function onbeforeedit(row)
    {
       debugger;
       var status=row.record.STATSUS;
       if(status=="1")
       {
           idp.warn("此条为已执行数据，不允许编辑。");
           return false;
       }
    }
    // function onunSelectRow(row) {
    //     debugger;
    //   row["CASH_FLOWS_PER_CNY100PAR"]=row["ACCRUED_PRINCIPAL_PER_CNY100PAR"]+row["ACCRUED_INTEREST_PER_CNY100PAR"];
    //   //idp.control.get("grid_194263").updateCell("CASH_FLOWS_PER_CNY100PAR", row["CASH_FLOWS_PER_CNY100PAR"], row.__index);


    // }

})

idp.event.bind("beforeEdit", function (e, data) {
    debugger;
});

let menu={
    saveandupdate:function(){
        debugger;
        var windcode= idp.utils.getQuery("windcode");
        let grid=idp.control.get("grid_194263");
        grid.endEdit();
        //let data=grid.getData();
        let rows=grid.rows;
        var resetrows=[];
        if(rows.length>0)
        {
            $.each(rows,function(index,item){
                if(item.__status=="update")
                {  
                    var hadrpayinterest=item.HADRPAYINTEREST;//实际已还利息
                    var hadrpayprincipal=item.HADRPAYPRINCIPAL;//实际已还本金
                    if(hadrpayinterest>0||hadrpayprincipal>0)
                    {
                       var indx=item.__index
                       idp.warn("第"+(index+1)+"条计划已执行，不允许修改。");
                       return false;
                    }

                    if(item.WINDCODE==null||item.WINDCODE==""||item.WINDCODE=="")
                    {
                        item.WINDCODE=windcode;
                    }
                    if(item.STATSUS==null||item.STATSUS==""||item.STATSUS=="")
                    {
                        item.STATSUS="0";
                    }
                    if(item.DELFLAG==null||item.DELFLAG==""||item.DELFLAG=="")
                    {
                        item.DELFLAG="0";
                    }
                    if(item.SOURCESYSTEM==null||item.SOURCESYSTEM==""||item.SOURCESYSTEM=="")
                    {
                        item.SOURCESYSTEM="1";
                    }
                    
                    if(item.CREATEDTIME==null||item.CREATEDTIME==""||item.CREATEDTIME=="")
                    {
                        item.CREATEDTIME=menu.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss");
                    }
                    if(item.CREATOR==null||item.CREATOR==""||item.CREATOR=="")
                    {
                        item.CREATOR=idp.context.get("UserId");
                    }
                    //if(item.LASTMODIFIER==null||item.LASTMODIFIER==""||item.LASTMODIFIER=="")
                    {
                        item.LASTMODIFIER=idp.context.get("UserId");
                    }
                    //if(item.LASTMODIFIEDTIME==null||item.LASTMODIFIEDTIME==""||item.LASTMODIFIEDTIME=="")
                    {
                        item.LASTMODIFIEDTIME=menu.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss");
                    }
                    delete item.__status;
                    delete item.__id;
                    delete item.__index;
                    delete item.__nextid;
                    delete item.__previd;
                    resetrows.push(item);
                }
            })
        }
        if(resetrows.length>0)
        {
            idp.service.fetch("/api/jtgk/fosunbond/v1.0/getfsun/savefosunbondrpayplans",
            {fosunbondrpayplansentity:JSON.stringify(resetrows),windcode:windcode},false).done(function(data1){
                if(data1.success)
                {
                    idp.warn("保存成功");
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
        var hadrpayinterest=data[0].HADRPAYINTEREST;//实际已还利息
        var hadrpayprincipal=data[0].HADRPAYPRINCIPAL;//实际已还本金
        if(hadrpayinterest>0||hadrpayprincipal>0)
        {
           idp.warn("此条计划已执行，不允许删除。");
           return false;
        }

        idp.confirm("确定要删除吗?",function(){
            var deleteStrs=data[0].ID;
            idp.service.fetch("/api/jtgk/fosunbond/v1.0/getfsun/deletefosunbondrpayplansbyid",
            {windcode:windcode,deleteids:deleteStrs},false).done(function(data1){
               if(data1.success)
               {
                   idp.warn("删除成功");
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
    
        debugger;
        for(var i=0;i<fosunbondrpayplans.length;i++)
        {
           if(!fosunbondrpayplans[i].ZJACCRUED_INTEREST_PER_CNY100PAR)
           {
             fosunbondrpayplans[i].ZJACCRUED_INTEREST_PER_CNY100PAR=fosunbondrpayplans[i].ACCRUED_INTEREST_PER_CNY100PAR;

           }
           if(!fosunbondrpayplans[i].ZJCASH_FLOWS_DATE)
           {
             fosunbondrpayplans[i].ZJCASH_FLOWS_DATE=fosunbondrpayplans[i].CASH_FLOWS_DATE;

           }
           if(!fosunbondrpayplans[i].ZJACCRUED_PRINCIPAL_PER_CNY100PAR)
           {
             fosunbondrpayplans[i].ZJACCRUED_PRINCIPAL_PER_CNY100PAR=fosunbondrpayplans[i].ACCRUED_PRINCIPAL_PER_CNY100PAR;

           }
           if(!fosunbondrpayplans[i].ZJCASH_FLOWS_PER_CNY100PAR)
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