
var issueregnumber="";
let dialog1,dialogright;
function showfosunbondissuedetails (ob){
     issueregnumber=$(ob).attr("issueregnumber");
     var issuershortened=$(ob).attr("issuershortened");
    var url = "/apps/fastdweb/views/runtime/page/query/querypreview.html?styleid=4007df94-4477-7ad8-d566-111b6f8e0ba0&status=edit&issueregnumber=" + escape(issueregnumber)+"&issuershortened="+escape(issuershortened);

    var options = {
      name: 'sonWindow',
      title: '债券发行明细',
      url: url,
      width: 980,
      height: 500,
      buttons: [
        { id: "LV_ok", text: '确定', cls: 'lee-btn-primary lee-dialog-btn-ok', onclick: onConfirm },
      { text: '取消', cls: 'lee-dialog-btn-cancel ', onclick: onCancel }
      ]
    };
    
    // 打开界面
    idp.dialogUrl(options, loadCallback, okCallBack, closeCallBack);
    //idp.utils.openurl('', '周转计划', url);
    
   }
function showattach (ob){
     issueregnumber=$(ob).attr("issueregnumber");
    var url ="/apps/fastdweb/views/runtime/page/card/cardpreview.html?styleid=d085b5b1-ade7-aae2-0a08-615366477ea1&status=edit&dataid=" +escape(issueregnumber);
    idp.utils.openurl('', '附件', url);
    
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
  //var issueregnumber=idp.utils.getQuery("issueregnumber");
  //var selectData=dialog.frame.idp.control.get("grid_main").allSelected;
  var selectData = dialog.frame.idp.control.get("grid_main").selected;
  //let gridDataRows = idp.control.get("grid_FOSUNREPAYMENTAPPSON").getData();
  //let mainData = idp.uiview.modelController.getMainRowObj();
  var ids="";
  $.each(selectData, function (index, item) {
 // let filterData = gridDataRows.filter(item1 => item1.PLANBID == item.UUID);
   ids+=(item.ID+";");
  });


idp.service.fetch("/api/jtgk/fosunbond/v1.0/getfsun/relationissueregnumber",{ids:ids,issueregnumber:issueregnumber},false).done(function(data1){

    if(data1.success)
    {
      idp.warn("关联成功");
      
      idp.uiview.refreshGrid("grid_360620");//刷新主列表
      if(dialogright)//刷新使用情况
      {
         dialogright.frame.idp.func.refresh("grid_main"); 
      }
  
    }
    else
    {
      idp.warn("关联失败");
    }
})





  dialog.close();




}

// 取消回调函数
function onCancel(item, dialog) { dialog.close(); }






showdetail = function(dataid) {
    if (!dataid) {
        idp.alert('请先选择行');
        return;
    }
    

    var options = {
        width: "100%",
        height: "40%",
        // bottom: 0,
        // top: "60%",
        helpID: '',
        id: 'billClaimDetail',
        title: '使用情况',
        url: '/apps/fastdweb/views/runtime/page/query/querypreview.html?styleid=3f284854-7d39-18b0-ea47-41ad95ea38ba&modid=3f284854-7d39-18b0-ea47-41ad95ea38ba&issueregnumber='+ escape(dataid)
    }

    idp.rightDrawer(options, function() {},function(){},function(){},function(){},delCallBack());
}

idp.rightDrawer = function(options, loadCallback, okCallBack, closeCallBack, onCopyBack, delCallBack) {
    var p = options
    p.width = p.width || 900;
    p.height = p.height || 580;
    p.helpID = p.helpID || "";
    p.title = p.title || "请选择";
    var title=$("<div class=tkjl>使用情况</div>")
    var opts = {
        title: '',
        // left:'70%',
        bottom: 0,
        top: "60%",
        name: 'customWindow',
        modal: false,
        title:title,
        isHidden: false,
        showMax: false,
        width: p.width,
        slide: true,
        height: p.height,
        url: p.url,
        fixedType: 'se',
        layoutMode: 2,
        isDrag: false,
        isResize: false,
        id: p.id || '',
        onclose: function() {
            closeCallBack && closeCallBack();
        },
        onLoaded: function() {
            loadCallback();
        }
    };

    if (options.buttons) opts.buttons = options.buttons;
    var dg = $.leeDialog.open(opts);
    dialogright=dg;
    return dg;
}

function delCallBack()
{
  debugger;
}
function loadCallback()
{
  debugger;
}



// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}



idp.event.bind("domReady", function() {

    idp.event.register("grid_360620","beforeGridInit",function(e,p){

    });
    //grid 数据加载后
    idp.event.register("grid_360620","afterLoadData",function(e,grid, ctrl, filter){
      debugger;
      //idp.control.get("lee-lightsolution--lee-lightsolution-grid_360620-light_ISMATURITY").filter.setValue("1");
      //idp.control.get("lee-lightsolution-grid_360620-light_ISMATURITY").filter2.setValue(versionDate);
      let filterData='';
      var data=idp.control.get("grid_360620").getData();
      var isexpired=$("#lee-lightsolution-grid_360620-light_ISMATURITY").leeUI().getValue();
      var isfirstquery=false;
      if(isexpired==""||isexpired==null)
      {
      
         filterData = data.filter(item1 => item1.ISMATURITY=="1");
         isfirstquery=true;
       
      }
       var issuershortened='' 
        if(idp.control.get("lee-lightsolution-grid_360620-light_ISSUERSHORTENED").helpVueIns)
        {
            issuershortened = idp.control.get("lee-lightsolution-grid_360620-light_ISSUERSHORTENED").helpVueIns.getValue();
        }
        if(issuershortened==""||issuershortened==null)
        {
             if(filterData==""&&isfirstquery==false)
             {
                  filterData = data.filter(item1 => item1.ISSUERSHORTENED=="复星高科");
             }
             else
             {
                if(filterData!="")
                {
                     filterData = filterData.filter(item1 => item1.ISSUERSHORTENED=="复星高科");
                }
               
             }

             isfirstquery=true;
        }
        
        if(filterData!="")
        {
             idp.control.get("grid_360620").loadData({Rows:filterData});
        }
        else if(isfirstquery)
        {
             idp.control.get("grid_360620").loadData({Rows:filterData});
        }
     


    });
    idp.event.register("grid_360620", "beforeGridFilter", function(e, filter) {
      // filter.push({
      //     Left: "",
      //     Value: '1',
      //     Right: "",
      //     Logic: "",
      //     Operate: "=", //操作符
      //     Field: "ISMATURITY" //要过滤的子弹
      // });

      // return filter;
  });

  idp.event.register("grid_360620", "beforeGridRefresh", function (e, filter) {
    // idp.utils.jsd("qUHZ92wAMVVYYBuSFZhPXA==")解密filter value值
//  debugger;
//  filter.push({
//   Left: "",
//   Value: '1',
//   Right: "",
//   Logic: "",
//   Operate: "=", //操作符
//   Field: "ISMATURITY" //要过滤的子弹
// });

// return filter;

});
});

function  getDate() {

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
  var nowDate = year + "-" + month + "-" + day;
  return nowDate;
}

