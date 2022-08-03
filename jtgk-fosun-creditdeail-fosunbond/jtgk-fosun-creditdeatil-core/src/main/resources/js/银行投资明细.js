idp.event.bind("viewReady",function(e,context){
    var querydate=idp.utils.getQuery("querydate");
    var varietiesid=idp.utils.getQuery("varietiesid")
    //varietiesid='65285f51-1218-ae32-e542-a1f029c7c550';
    //querydate='2022-06-20';
    if(querydate)
    {
        idp.service.fetch("/api/jtgk/fosunbond/v1.0/getfsun/getbankbondinvestmentdetails",{querydate:querydate,varietiesid:varietiesid},false).done(function(data1){
        if(data1.success)
        {
            var griddata=data1.result;
            var resetgriddata=[];
            if(griddata && griddata.length>0)
            {
                $.each(griddata,function(index,item){
                    resetgriddata.push(key2Upper(item));
                    
                })
                
                idp.control.get("grid_455036").loadData({Rows:resetgriddata});
            
            }
        
        }
        else
        {
            idp.warn(data1.message);
        }
    })
        
    }
})
function showsxht(obj)
{
    var sxhtcode=$(obj).attr("sxhtcode");
    var url = "/apps/fastdweb/views/runtime/page/card/cardpreview.html?styleid=183cd2d1-6b13-b625-61fa-dfa045dd6ca3&status=add&sxhtcode=" + sxhtcode;
    idp.utils.openurl('','授信合同',url);
}
function key2Upper(obj)
{
    for(var key in obj)
    {
        obj[key.toUpperCase()]=obj[key];
        delete(obj[key]);
    }
    return obj;
}