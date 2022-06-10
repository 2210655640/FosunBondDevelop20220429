function showrepaymentplan (ob){
    var windcode=$(ob).attr("windcode");
    var url = "/apps/fastdweb/views/runtime/page/card/cardpreview.html?styleid=183cd2d1-6b13-b625-61fa-dfa045dd6ca3&status=add&windcode=" + windcode;

    var options = {
      name: 'sonWindow',
      title: '还款计划',
      url: url,
      width: 980,
      height: 500,
      buttons: [
       // { id: "LV_ok", text: '确定', cls: 'lee-btn-primary lee-dialog-btn-ok', onclick: onConfirm },
      //{ text: '取消', cls: 'lee-dialog-btn-cancel ', onclick: onCancel }
      ]
    };
    
    // 打开界面
    idp.dialogUrl(options, loadCallback, okCallBack, closeCallBack);
    //idp.utils.openurl('', '周转计划', url);
    
   }

function showholder(ob)
{
    debugger;
    var sec_name=$(ob).attr("sec_name");
    var url="/apps/fastdweb/views/runtime/page/card/cardpreview.html?styleid=69ba65e2-9176-499d-b732-d68b147570aa&dataid=&status=add&j=true&secname="+escape(sec_name);
    idp.utils.openurl('','持有人',url);
}



// 弹窗加载后回调函数
function loadCallback(item, dialog) {

}

// 确定回调函数
function okCallBack(item, dialog) {
 
}

// 关闭回调函数
function closeCallBack() {

}

// 确定回调函数
function onConfirm(item, dialog) {

  debugger;
  //var selectData=dialog.frame.idp.control.get("grid_main").allSelected;
  var selectData = dialog.frame.idp.control.get("grid_main").selected;
  let gridDataRows = idp.control.get("grid_FOSUNREPAYMENTAPPSON").getData();
  let mainData = idp.uiview.modelController.getMainRowObj();
  $.each(selectData, function (index, item) {
    //selectedids.push(item.uuid);
    //判断planID是否已经存在，存在则不需要添加
    let filterData = gridDataRows.filter(item1 => item1.PLANBID == item.UUID);
    if (filterData.length <= 0) {
      var datagridrow = {};
      datagridrow["MAINID"] = mainData.ID;
      datagridrow["PLANBID"] = item.UUID;
      datagridrow["SUMMARY"] = '';
      //datagridrow["LASTMODIFIER"]='';
      //datagridrow["CREATEDTIME"]='';
      //datagridrow["CREATOR"]='';
      datagridrow["ID"] = Guid.NewGuid().ToString();;
      //datagridrow["LASTMODIFIEDTIME"]='';
      datagridrow["STATUS"] = '';
      datagridrow["BORROWER"] = item.BORROWER;
      datagridrow["FINANCIALINSTITUTION"] = item.FINANCIALINSTITUTION;
      datagridrow["WITHDRAWALNUM"] = item.WITHDRAWALNUM;
      datagridrow["DUEDATE"] = item.DUEDATE;
      datagridrow["REPAYPRINCIPAL"] = item.REPAYPRINCIPAL;
      datagridrow["REPAYINTEREST"] = item.REPAYINTEREST;
      datagridrow["CONTRACTNUM"] = item.CONTRACTNUM;
      datagridrow["UUID"] = item.UUID;
      gridDataRows.push(datagridrow);
    }

  });

  //$("#grid_FOSUNREPAYMENTAPPSON").loadData({Rows:gridDataRows});
  idp.control.get("grid_FOSUNREPAYMENTAPPSON").loadData({ Rows: gridDataRows });
  //自动设置还息金额 还本金额 还款金额合计
  $.each(gridDataRows, function (index2, gridrow) {
    suminterestamount += gridrow.REPAYINTEREST;
    sumprincipalamount += gridrow.REPAYPRINCIPAL;
  })
  idp.uiview.setCtrlValue("PRINCIPALAMOUNT", sumprincipalamount);
  idp.uiview.setCtrlValue("INTERESTAMOUNT", suminterestamount);
  idp.uiview.setCtrlValue("SUMAMOUNT", sumprincipalamount + suminterestamount);





  dialog.close();




}

// 取消回调函数
function onCancel(item, dialog) { dialog.close(); }

idp.event.bind("domReady", function () {
    idp.loading();
    //改图标
    var content = `<img src="/apps/sankey/img/页面icon.svg">`
    $(".header-icon").append(content);
    console.log('123')
    //
    idp.event.register("grid_main", "beforeGridInit", function (e, p) {
        
        //p.groupColumns = ["COMP_NAME", "BONDTYPE"];
        p.groupColumns = ["ISSUERSHORTENED", "BONDTYPE"];
        // p.columns[1].lazy = true;
        // p.columns[1].lazyRender = function(row,index,value,column){

        //     console.log(row,index,value,column)

        // }
        // console.log($('.lee-grid-totalsummary-group'))
        // $('.lee-grid-totalsummary-group .lee-grid-totalsummary-cell-inner')[0].text='合计：'

    })
    idp.event.bind("afterEdit", function () {
       
        $("#input_5057").leeTextBox({disabled:false});
        $("#COMP_NAME").leeTextBox({disabled:false});
        $("#BONDTYPE").leeTextBox({disabled:false});
        $("#CARRYDATE").leeTextBox({disabled:false});
        $("#MATURITYDATE").leeTextBox({disabled:false});
        $("#SEC_NAME").leeTextBox({disabled:false});
    
    })
    idp.event.register("grid_main", "beforeGridRefresh", function (e, filter) {
         // idp.utils.jsd("qUHZ92wAMVVYYBuSFZhPXA==")解密filter value值
      debugger;
      menu.query();
      return false;
 
    });
    idp.event.register("grid_main", "afterLoadData", function (e, p) {
        console.log(456)
        // p.columns[1].lazy = true;
        // p.columns[1].lazyRender = function(row,index,value,column){

        //     console.log(row,index,value,column)

        // }
        // console.log($('.lee-grid-totalsummary-group'))
        
        console.log($(".lee-grid-totalsummary").previousSibling)

        // let m = $('.lee-grid-totalsummary-group').length;
        // for (let i = 0; i < m; i++) {
        //     var basicele=$('.lee-grid-totalsummary-group')[i].children[9];
        //     if(basicele)
        //     {
        //         var basiclechild=basicele.children[0].children[0];
        //         if(basiclechild)
        //         {
        //             var originalSum = basiclechild.innerText;
        //             basicele.children[0].children[0].innerText = originalSum.replace("合计:", "");
        //         }
               
        //         var bondtypeStr = $('.lee-grid-totalsummary-group')[i].previousSibling.children[1].children[0].innerText;
        //         $('.lee-grid-totalsummary-group')[i].children[0].children[0].innerText = bondtypeStr + "小计:";
                
        //     }
      

        // }


    })

});

function setTableOption(){
    const n = $('.lee-grid-grouprow-cell').length;
        let replaceStr = "发行主体";
        for (let i = 0; i < n; i++) {
            let totalstr = $('.lee-grid-grouprow-cell')[i].innerText;
            /*$('.lee-grid-grouprow-cell')[i].innerHTML = '<span class="lee-icon lee-grid-group-togglebtn"></span>' + totalstr.substr(3);*/
            //$('.lee-grid-grouprow-cell')[i].innerHTML = '<span class="lee-icon lee-grid-group-togglebtn"></span>' + '发行主体' + totalstr.substr(3);
            
            if($('.lee-grid-grouprow td')[i].style.paddingLeft === '20px'){
                replaceStr = "债券种类";
                 $('.lee-grid-grouprow-cell')[i].style = "padding-left:44px !important;font-family: PingFangSC-Medium;font-size: 13px;color: #424347;font-weight: 500;";
            }else{
                replaceStr = "发行主体";
                  //改颜色
                $('.lee-grid-grouprow-cell')[i].style = "font-family: PingFangSC-Medium;font-size: 13px;color: #2A87FF;font-weight: 500;padding-left:24px !important;background: #F7F8FB;";
            }
            //console.log(replaceStr)
             $('.lee-grid-grouprow-cell')[i].innerHTML = '<span class="lee-icon lee-grid-group-togglebtn"></span>' + totalstr.replace("分组", replaceStr);
        }
        let m = $('.lee-grid-totalsummary-group .lee-grid-totalsummary-cell-inner').length;
        for(let i =0;i<m;i++){
            if(i % 18 == 1){
                const totalsummaryIndex = parseInt(i/18);
                var summay=$(".lee-grid-totalsummary")[totalsummaryIndex].previousSibling.children[2];
                if(summay)
                {
                    const pre = summay.children[0].innerText;
                    $('.lee-grid-totalsummary-group .lee-grid-totalsummary-cell-inner')[i].innerHTML = pre + '小计：';
                }
       
            }
        }
}
idp.event.bind("viewReady", function (e, context) {
    idp.loading();
    $("#grid_main").leeGrid({
        onDblClickRow: ondblclickrow
    });
    function ondblclickrow(row) {
        debugger;
        idp.utils.openurl(row["ID"], "债券信息详情",
            ("/apps/fastdweb/views/runtime/page/card/cardpreview.html?dataid=" + row["ID"] + "&status=view" + "&styleid=a92acb15-453f-4db7-a1fd-fb8681357143")
            , true
        )

    }

    $("#input_5057").leeTextBox({disabled:false});
    $("#COMP_NAME").leeTextBox({disabled:false});
    $("#BONDTYPE").leeTextBox({disabled:false});
    $("#CARRYDATE").leeTextBox({disabled:false});
    $("#MATURITYDATE").leeTextBox({disabled:false});
    $("#SEC_NAME").leeTextBox({disabled:false});
    menu.cancel();
    menu.query();

});
let menu = {
    cancel:function(){
        $("a[toolbarid='baritem_modify']").attr("class","lee-btn  lee-toolbar-item lee-btn-default l-toolbar-item-hasicon");//设置按钮不可用
        $("a[toolbarid='baritem_modify']").removeAttr("disabled");//设置按钮不可用
        $("a[toolbarid='baritem_cancel']").attr("class","lee-btn  lee-toolbar-item lee-btn-default l-toolbar-item-hasicon lee-toolbar-item-disable");//可用
        $("a[toolbarid='baritem_cancel']").attr("disabled","disabled");
        $("a[toolbarid='baritem_save']").attr("class","lee-btn  lee-toolbar-item lee-btn-default l-toolbar-item-hasicon lee-toolbar-item-disable");//可用
        $("a[toolbarid='baritem_save']").attr("disabled","disabled");
        $('#grid_main').leeGrid({enabledEdit:false});
    },
    update:function()
    {
        idp.uiview.edit(true);
        $("a[toolbarid='baritem_modify']").attr("class","lee-btn  lee-toolbar-item lee-btn-default l-toolbar-item-hasicon lee-toolbar-item-disable");//设置按钮不可用
        $("a[toolbarid='baritem_modify']").attr("disabled","disabled");//设置按钮不可用
        $("a[toolbarid='baritem_cancel']").attr("class","lee-btn  lee-toolbar-item lee-btn-default l-toolbar-item-hasicon");//可用
        $("a[toolbarid='baritem_cancel']").removeAttr("disabled");
        $("a[toolbarid='baritem_save']").attr("class","lee-btn  lee-toolbar-item lee-btn-default l-toolbar-item-hasicon");//可用
        $("a[toolbarid='baritem_save']").removeAttr("disabled");
        $('#grid_main').leeGrid({enabledEdit:true});
    },
    query: function () {
        debugger;
        //idp.loading("加载中");
        //var versionDate = idp.control.get("input_5057").getValue();
        //var versionDate =$("#input_5057").val();
        // if (versionDate == "" || versionDate == null) 
        // {
        //     //idp.control.get("grid_main").reload();//重新加载数据
        //     //idp.uiview.reloadData();
        //     idp.service.requestApi("/Card/getCardData", JSON.stringify({
        //         id:"ac72c957-be2e-2d6a-1e11-e6b6d8f6494e",
        //         styleId: "7630a315-dc53-acb1-26c0-baa2578e50fc"
        //     }), false).done(function (bondModelData) {
        //         if (bondModelData.Code == "ok") {
        //             data = bondModelData.Data[0].data;
        //         }
        //     });

        //     idp.uiview.refreshGrid("grid_main");
        // }
        // else 
        {    
            // var com_name = idp.control.get("COMP_NAME").getValue();
            // var sec_name = idp.control.get("SEC_NAME").getValue();
            // var bondtype = idp.control.get("BONDTYPE").getValue();
            // var carrydate = idp.control.get("CARRYDATE").getValue();
            // var maturitydate = idp.control.get("MATURITYDATE").getValue();
            //var isexpired=$("#checkbox_457018").leeUI().getValue();
            var versionDate='';
            if(idp.control.get("lee-lightsolution--light_historyversiondate").helpVueIns)
            {
                versionDate= idp.control.get("lee-lightsolution--light_historyversiondate").helpVueIns.getValue();
            }
            var com_name='' 
            if(idp.control.get("lee-lightsolution--light_ISSUERSHORTENED").helpVueIns)
            {
                com_name = idp.control.get("lee-lightsolution--light_ISSUERSHORTENED").helpVueIns.getValue().replaceAll('复星高科','复星');
            }
            var sec_name ='';
            if(idp.control.get("lee-lightsolution--light_SEC_NAME").helpVueIns)
            {
                sec_name=idp.control.get("lee-lightsolution--light_SEC_NAME").helpVueIns.getValue(); 
            }
            var bondtype ='';
            if(idp.control.get("lee-lightsolution--light_BONDTYPE").helpVueIns)
            {
             bondtype = idp.control.get("lee-lightsolution--light_BONDTYPE").helpVueIns.getValue();
            }
            var carrydate =  $("#lee-lightsolution--light_CARRYDATE").leeUI().getValue();
            var maturitydate =  $("#lee-lightsolution--light_MATURITYDATE ").leeUI().getValue();
            var isexpired=$("#lee-lightsolution--light_dropdown_isexpired").leeUI().getValue();
            if(com_name=="复星高科")
            {
                com_name="复星";
            }
            if(versionDate==null)
            {
                versionDate="";
            }
            if(carrydate==null)
            {
                carrydate="";
            }
            if(maturitydate==null)
            {
                maturitydate="";
            }
           
            idp.service.fetch("/api/jtgk/fosunbond/v1.0/getfsun/getfosundebtcon",
                { versiondate: versionDate, com_name: com_name,sec_name:sec_name, bondtype: bondtype, carrydate: carrydate, maturitydate: maturitydate,isexpired:isexpired }, false).done(function (data) {
                    debugger;
                    if (data.success) {
                        var fosunDebtContractHistoryEntityList = data.result;
                        // let g = idp.control.get("grid_main");
                        // $('#grid_main').leeGrid({
                        //     toobar: [],
                        //     columns: g.options.columns,
                        //     data: {Rows: fosunDebtContractHistoryEntityList},
                        //     usePager: false,
                        //     height: '100%',
                        //      rowHeight: 30,
                        //     frozen: true,
                        //     enabledSort: false,
                        //     excel: true
                        // });
                        if(fosunDebtContractHistoryEntityList)
                        {
                            $.each(fosunDebtContractHistoryEntityList,function(index,item){
                                if(item.ISSUERSHORTENED=="复星")
                                {
                                    fosunDebtContractHistoryEntityList[index].ISSUERSHORTENED="复星高科";
                                }
                                if(item.ISSUEAMOUNT)
                                {
                                    fosunDebtContractHistoryEntityList[index].ISSUEAMOUNT=item.ISSUEAMOUNT/100000000;
                                }
                                     //处理债券类型 
                                var fullname=item.FULLNAME;
                                var bondtype=item.BONDTYPE;
                                if ((bondtype==""||bondtype==null)&&fullname!=undefined&&fullname!=null
                                &&fullname.indexOf("自由贸易")>-1) {
                                    item.BONDTYPE="自贸债";
                                } 
                            });
                        }
                        else
                        {
                            fosunDebtContractHistoryEntityList=[];
                        }
                
                        idp.loaded();
                        idp.control.get("grid_main").loadData({ Rows: fosunDebtContractHistoryEntityList });
                        setTimeout(function() {
                            setTableOption();
                        }, 10);
                        

                    } else {
                        idp.error("请求失败");
                    }
                });

        }
    },
    saveandquery: function () {
        debugger;
        idp.loading("保存中");
       
        // var versionDate = idp.control.get("input_5057").getValue();
        // var com_name = idp.control.get("COMP_NAME").getValue();
        // var sec_name = idp.control.get("SEC_NAME").getValue();
        // var bondtype = idp.control.get("BONDTYPE").getValue();
        // var carrydate = idp.control.get("CARRYDATE").getValue();
        // var maturitydate = idp.control.get("MATURITYDATE").getValue();
        // var isexpired=$("#checkbox_457018").leeUI().getValue();
        var versionDate='';
        if(idp.control.get("lee-lightsolution--light_historyversiondate").helpVueIns)
        {
            versionDate= idp.control.get("lee-lightsolution--light_historyversiondate").helpVueIns.getValue();
        } 
        var com_name='' 
        if(idp.control.get("lee-lightsolution--light_ISSUERSHORTENED").helpVueIns)
        {
            com_name = idp.control.get("lee-lightsolution--light_ISSUERSHORTENED").helpVueIns.getValue().replaceAll('复星高科','复星');
        }
        var sec_name ='';
        if(idp.control.get("lee-lightsolution--light_SEC_NAME").helpVueIns)
        {
            sec_name=idp.control.get("lee-lightsolution--light_SEC_NAME").helpVueIns.getValue(); 
        }
        var bondtype ='';
        if(idp.control.get("lee-lightsolution--light_BONDTYPE").helpVueIns)
        {
         bondtype = idp.control.get("lee-lightsolution--light_BONDTYPE").helpVueIns.getValue();
        }
        var carrydate =  $("#lee-lightsolution--light_CARRYDATE").leeUI().getValue();
        var maturitydate =  $("#lee-lightsolution--light_MATURITYDATE ").leeUI().getValue();
        var isexpired=$("#lee-lightsolution--light_dropdown_isexpired").leeUI().getValue();
        if(versionDate==null||versionDate=="")
        {
            //versionDate="";
            versionDate=menu.getDate().split(" ")[0];
            $("#input_5057").val(versionDate);
            //$("#input_5057").val("2022-05-08");
        }
        if(carrydate==null)
        {
            carrydate="";
        }
        if(maturitydate==null)
        {
            maturitydate="";
        }

        let grid = idp.control.get("grid_main");
        grid.endEdit();
        idp.loading();
        let data = grid.getData();
        if (data.length > 0) {
            var isversiondate = data[0].HISTORYVERSIONDATE;
            if(isversiondate==""||isversiondate==null)
            {
                isversiondate=menu.getDate();
            }
            //if(isversiondate!=""&&isversiondate!=null)//保存历史版本记录表
            {
                for (var i = 0; i < data.length; i++) {
                    data[i].SOURCECONTRACTID = data[i].ID;
                    if(data[i].HISTORYVERSIONDATE==""||data[i].HISTORYVERSIONDATE==null)
                    {
                        data[i].HISTORYVERSIONDATE=isversiondate;
                    }
                }
                idp.service.fetch("/api/jtgk/fosunbond/v1.0/getfsun/savefosundebtcontracthistorybyversiondate",
                    { historyentity: JSON.stringify(data),versiondate: versionDate, com_name: com_name,sec_name:sec_name, bondtype: bondtype, carrydate: carrydate, maturitydate: maturitydate,isexpired:isexpired }, false).done(function (data1) {
                        debugger;
                        if (data1.success) {
                            var fosunDebtContractHistoryEntityList = data1.result;
                            if(fosunDebtContractHistoryEntityList)
                            {
                                $.each(fosunDebtContractHistoryEntityList,function(index,item){
                                    if(item.ISSUERSHORTENED=="复星")
                                    {
                                        fosunDebtContractHistoryEntityList[index].ISSUERSHORTENED="复星高科";
                                    }
                                    if(item.ISSUEAMOUNT)
                                    {
                                        fosunDebtContractHistoryEntityList[index].ISSUEAMOUNT=item.ISSUEAMOUNT/100000000;
                                    }
                                         //处理债券类型 
                                var fullname=item.FULLNAME;
                                var bondtype=item.BONDTYPE;
                                if ((bondtype==""||bondtype==null)&&fullname!=undefined&&fullname!=null
                                &&fullname.indexOf("自由贸易")>-1) {
                                    item.BONDTYPE="自贸债";
                                } 
                                });
                            }
                            else
                            {
                                fosunDebtContractHistoryEntityList=[];
                            }
                  
                            idp.loaded();
                            
                        let g = idp.control.get("grid_main");
                        // $('#grid_main').leeGrid({
                        //     toobar: [],
                        //     columns: g.options.columns,
                        //     data: {Rows: fosunDebtContractHistoryEntityList},
                        //     usePager: false,
                        //     height: '100%',
                        //      rowHeight: 30,
                        //     frozen: true,
                        //     enabledSort: false,
                        //     excel: true,
                        //     enabledEdit:false
                        // });
                     
                        idp.control.get("grid_main").loadData({ Rows: fosunDebtContractHistoryEntityList });
                        idp.warn("保存成功");
                        $("a[toolbarid='baritem_modify']").attr("class","lee-btn  lee-toolbar-item lee-btn-default l-toolbar-item-hasicon");//设置按钮不可用
                        $("a[toolbarid='baritem_modify']").removeAttr("disabled");//设置按钮不可用
                        $("a[toolbarid='baritem_cancel']").attr("class","lee-btn  lee-toolbar-item lee-btn-default l-toolbar-item-hasicon lee-toolbar-item-disable");//可用
                        $("a[toolbarid='baritem_cancel']").attr("disabled","disabled");
                        $("a[toolbarid='baritem_save']").attr("class","lee-btn  lee-toolbar-item lee-btn-default l-toolbar-item-hasicon lee-toolbar-item-disable");//可用
                        $("a[toolbarid='baritem_save']").attr("disabled","disabled");
                        $('#grid_main').leeGrid({enabledEdit:false});//结束编辑状态
                        setTableOption();
               

                        } else {
                            idp.error("请求失败");
                        }
                    });
            }
            // else//保存默认表
            {
                // return idp.uiview.saveData();
            }
        }

        //idp.uiview.endEdit();
        //var defer = $.Deferred();
        idp.uiview.endEdit();
        //idp.uiview.gridController.refresh();
        idp.uiview.cleanValidate();
        //defer.resolve(true);

    },
    getDate: function () {

        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        var nowDate = year + "-" + month + "-" + day+" "+"00:00:00";
        return nowDate;
    }


}