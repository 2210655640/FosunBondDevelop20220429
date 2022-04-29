idp.event.bind("domReady",function(e,context){
    debugger;
    
    $("#INTERESTAMOUNT").leeTextBox({
        onChangeValue(value){
            var principalamount= idp.uiview.modelController.getValue("PRINCIPALAMOUNT");
           
         idp.uiview.setCtrlValue("SUMAMOUNT", Number(value) + Number(principalamount));
        }
    })
    
     $("#PRINCIPALAMOUNT").leeTextBox({
        onChangeValue(value){
            var interestamount= idp.uiview.modelController.getValue("INTERESTAMOUNT");
         idp.uiview.setCtrlValue("SUMAMOUNT", Number(value) + Number(interestamount));
        }
    })
    //子表删除行后
 idp.event.register("grid_FOSUNREPAYMENTAPPSON","afterDeleteGridRow",function(e,data){
   var suminterestamount=0;
   var sumprincipalamount=0;
   let gridDataRows = idp.control.get("grid_FOSUNREPAYMENTAPPSON").getData();
   $.each(gridDataRows, function (index2, gridrow) {
     suminterestamount += gridrow.REPAYINTEREST;
     sumprincipalamount += gridrow.REPAYPRINCIPAL;
   })
   idp.uiview.setCtrlValue("PRINCIPALAMOUNT", sumprincipalamount);
   idp.uiview.setCtrlValue("INTERESTAMOUNT", suminterestamount);
   idp.uiview.setCtrlValue("SUMAMOUNT", sumprincipalamount + suminterestamount);
 });
    
     
 });

 idp.event.bind("loadData",function(){
  
 });
 idp.event.bind("viewReady",function(){

   debugger;
   var dealstatus=idp.utils.getQuery("status");
   if(dealstatus=="add")
   {

      
       $("[toolbarid=baritem_submit],[toolbarid=baritem_delete]").attr("disabled","disabled");
       $("[toolbarid=baritem_submit],[toolbarid=baritem_delete]").attr("class","lee-btn  lee-toolbar-item lee-btn-default l-toolbar-item-hasicon lee-toolbar-item-disable");

   }

 });
 
 