idp.event.bind("domReady", function() { //需要在界面加载后绑定
    debugger;
    idp.event.register("grid_main", "beforeGridFilter", function(e, filter) {
        filter.push({
            Left: "",
            Value: unescape(idp.utils.getQuery("issueregnumber")),
            Right: "",
            Logic: "",
            Operate: "=", //操作符
            Field: "ISSUE_REGNUMBER" //要过滤的子弹
        });

        return filter;
    });
});

function cancelrelationissueregnumber(ob)
{
    debugger;
    var id=$(ob).attr("id");
    var isoriginalrelationrenum=$(ob).attr("isoriginalrelationrenum");
    if(isoriginalrelationrenum=="1")
    {
        idp.warn("原始设置注册文号不允许删除");
        return false;
    }
    idp.confirm("确认要删除吗?",function(){
        idp.service.fetch("/api/jtgk/fosunbond/v1.0/getfsun/cancelrelationissueregnumber",{cancelId:id},false).done(function(data1){
            if(data1.success)
            {
                idp.warn("删除成功");
                //idp.uiview.refreshGrid("grid_main");
                idp.list.func.refresh();
                window.parent.idp.uiview.refreshGrid("grid_360620");
            }
            else
            {
                
            }
        })
    },function(){
        
    })
}