idp.event.bind("domReady", function () {


    idp.event.register("grid_main", "beforeGridInit", function (e, p) {

        p.groupColumns = ["COMP_NAME", "BONDTYPE"];
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
    idp.event.register("grid_main", "beforeGridFilter", function (e, filter) {
        debugger;
        var com_name = idp.control.get("COMP_NAME").getValue();
        var bondtype = idp.control.get("BONDTYPE").getValue();
        var carrydate = idp.control.get("CARRYDATE").getValue();
        var maturitydate = idp.control.get("MATURITYDATE").getValue();
        //采购方案编号
        //let rfqid = idp.utils.getQuery("rfqid"); //获取采购方案code
        if (com_name != "" && com_name != null) {
            filter.push({
                "Left": "",
                "Field": "FOSUNDEBTCONTRACT.COMP_NAME",
                "Operate": "=",
                "IsExpress": false,
                "Value": com_name,
                "Right": "",
                "Logic": " and "
            });
        }
        if (bondtype != "" && bondtype != null) {
            filter.push({
                "Left": "",
                "Field": "FOSUNDEBTCONTRACT.BONDTYPE",
                "Operate": "=",
                "IsExpress": false,
                "Value": bondtype,
                "Right": "",
                "Logic": " and "
            });
        }
        if (carrydate != "" && carrydate != null) {
            filter.push({
                "Left": "",
                "Field": "FOSUNDEBTCONTRACT.CARRYDATE",
                "Operate": "=",
                "IsExpress": false,
                "Value": carrydate,
                "Right": "",
                "Logic": " and "
            });
        }
        if (maturitydate != "" && maturitydate != null) {
            filter.push({
                "Left": "",
                "Field": "FOSUNDEBTCONTRACT.MATURITYDATE",
                "Operate": "=",
                "IsExpress": false,
                "Value": maturitydate,
                "Right": "",
                "Logic": "  "
            });
        }
        return filter;
    });
    idp.event.register("grid_main", "afterLoadData", function (e, p) {

        // p.columns[1].lazy = true;
        // p.columns[1].lazyRender = function(row,index,value,column){

        //     console.log(row,index,value,column)

        // }
        // console.log($('.lee-grid-totalsummary-group'))
        const n = $('.lee-grid-grouprow-cell').length;
        for (let i = 0; i < n; i++) {
            let totalstr = $('.lee-grid-grouprow-cell')[i].innerText;
            /*$('.lee-grid-grouprow-cell')[i].innerHTML = '<span class="lee-icon lee-grid-group-togglebtn"></span>' + totalstr.substr(3);*/

            //$('.lee-grid-grouprow-cell')[i].innerHTML = '<span class="lee-icon lee-grid-group-togglebtn"></span>' + '发行主体' + totalstr.substr(3);
            $('.lee-grid-grouprow-cell')[i].innerHTML = '<span class="lee-icon lee-grid-group-togglebtn"></span>' + totalstr.replace("分组:", "发放主体");


        }
        console.log($(".lee-grid-totalsummary").previousSibling)

        let m = $('.lee-grid-totalsummary-group').length;
        for (let i = 0; i < m; i++) {
            var basicele=$('.lee-grid-totalsummary-group')[i].children[9];
            if(basicele)
            {
                var basiclechild=basicele.children[0].children[0];
                //if(basiclechild)
                {
                    var originalSum = basiclechild.innerText;
                    basicele.children[0].children[0].innerText = originalSum.replace("合计:", "");
                }
               
                var bondtypeStr = $('.lee-grid-totalsummary-group')[i].previousSibling.children[1].children[0].innerText;
                $('.lee-grid-totalsummary-group')[i].children[0].children[0].innerText = bondtypeStr + "小计:";
            }
      

        }


    })

});
idp.event.bind("viewReady", function (e, context) {

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

});
let menu = {
    query: function () {
        debugger;
        idp.loading();
        var versionDate = idp.control.get("input_5057").getValue();
    
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
            var com_name = idp.control.get("COMP_NAME").getValue();
            var sec_name = idp.control.get("SEC_NAME").getValue();
            var bondtype = idp.control.get("BONDTYPE").getValue();
            var carrydate = idp.control.get("CARRYDATE").getValue();
            var maturitydate = idp.control.get("MATURITYDATE").getValue();
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
                { versiondate: versionDate, com_name: com_name,sec_name:sec_name, bondtype: bondtype, carrydate: carrydate, maturitydate: maturitydate }, false).done(function (data) {
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
                        idp.loaded();
                        idp.control.get("grid_main").loadData({ Rows: fosunDebtContractHistoryEntityList });
                       

                    } else {
                        idp.error("请求失败");
                    }
                });

        }
    },
    saveandquery: function () {
        debugger;
        idp.loading();
       
        var versionDate = idp.control.get("input_5057").getValue();
        var com_name = idp.control.get("COMP_NAME").getValue();
        var sec_name = idp.control.get("SEC_NAME").getValue();
        var bondtype = idp.control.get("BONDTYPE").getValue();
        var carrydate = idp.control.get("CARRYDATE").getValue();
        var maturitydate = idp.control.get("MATURITYDATE").getValue();
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

        let grid = idp.control.get("grid_main");
        let data = grid.getData();
        if (data.length > 0) {
            var isversiondate = data[0].versionDate;
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
                    { historyentity: JSON.stringify(data),versiondate: versionDate, com_name: com_name,sec_name:sec_name, bondtype: bondtype, carrydate: carrydate, maturitydate: maturitydate }, false).done(function (data1) {
                        debugger;
                        if (data1.success) {
                            var fosunDebtContractHistoryEntityList = data1.result;
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