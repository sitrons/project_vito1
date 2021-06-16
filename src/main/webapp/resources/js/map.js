
 console.log("[map.js]  작동중...........");
 
 
 var mapService = (function(){
 
 	function show(rno, callback, error) {
 		$.get("/replies/"+ rno, function(result){
 			if(callback){
 				callback(result);
 			}
 		}).fail(function(xhr, status, err){
 			if(error){
 				error();
 			}
 		});
 	}
 	
 	return {
 			show : show
 			};     
 })();
 