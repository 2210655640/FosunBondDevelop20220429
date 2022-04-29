idp.event.bind("domReady",function(e,context){
   


    
});

idp.event.bind("loadData",function(){
    var data= idp.uiview.modelController.deafaultData;
    if (data!=null) {
       var condata=idp.uiview.modelController.deafaultData[0].data[0];
       if(condata!=null)
       {
           //设置发行人简称
           var fullname=condata.FULLNAME;//债券全称
           var bondtype=condata.BONDTYPE;//债券类型
           var  issushortend=condata.ISSUERSHORTENED==null?"":condata.ISSUERSHORTENED;//设置发行人简称
           var  agencygrnttype=condata.AGENCY_GRNTTYPE==null?"":condata.AGENCY_GRNTTYPE;//担保方式
           if(issushortend=="复兴")
           {
               idp.uiview.setCtrlValue("ISSUERSHORTENED","复兴集团"); 
               
           }
           issushortend=condata.ISSUERSHORTENED;
           var issu=$("#COMP_NAME").val();
           $("#input_347322").val(agencygrnttype+issu);//增信情况
           var interest=condata.INTERESTFREQUENCY;//付息频率
           $("#INTERESTFREQUENCY").val("每年付息"+interest+"次");
           //债券类型
           if(bondtype==""||bondtype==null)
           {
               if(fullname!=null&&fullname!=""&&fullname.indexOf("自由贸易")>-1)
               {
                 $("#input_684338").val("自贸债");
               }
           }
           
       }
         
    } 
    
    
 

 
});
idp.event.bind("viewReady",function(){

  

});