idp.event.bind("domReady",function(){
   
    var issuershortend= unescape(idp.utils.getQuery("issuershortened"));
      idp.event.register("grid_main", "beforeGridFilter", function(e, filter) {
          debugger;
       filter.push({
           Left: "",
           Value: issuershortend,
           Right: "",
           Logic: "",
           Operate: "=", //操作符
           Field: "ISSUERSHORTENED" //要过滤的字段
       });

       return filter;
  });
})