var btn = document.getElementById("loginButton"); 
var result ="";
var httpRequest = new XMLHttpRequest();
var user_id = ""
// var dataloadNavbar = (function(){
// 	function loadNavbar(data, callback, error){
// 		console.log("start fn");
// 		$.ajax({
// 			type : "POST",
// 			url : "/dataload/navbar2",
// 			data : JSON.stringify(data),
// 			contentType : "application/json; charset=utf-8",
// 			success : function(res, status, xhr){
// 				if(callback){
// 					callback(res);
// 					var outVO=res.navbaroutVO;
// 					console.log("success connection");
// 				}
// 			},
// 			error : function(xhr, status, er){
// 				if(error){
// 					error(er);
// 					console.log("fail connection");
// 				}
// 			}
// 		})
// 	}return {
// 		loadNavbar : loadNavbar
// 	};
// })();

// btn.addEventListener('click', () => {
// 	user_id = document.getElementById("sideRemoteId").value;
// 	// var value = '<c:out value = "${board.bno}/>';
// 	dataloadNavbar.loadNavbar({
// 		"user_id" : this.user_id
// 	}, 
// 	function(result){
// 		alert(result);
// 	});
// });


    	btn.addEventListener('click', () => {
		user_id = document.getElementById("sideRemoteId").value;
		var sendData = new Object();
		var maindataLoadInVO = {
			"user_id" : user_id
		}
		sendData.maindataLoadInVO = maindataLoadInVO;
	    httpRequest.onreadystatechange = () => {
            if (httpRequest.readyState == 4) {
				if( httpRequest.status === 200 ){
					result = httpRequest.response;
					console.log(result);
					alert(result);
				}
				else {
					alert('Request Error!');
		   		}
			} 
	    };
	    /* Get 방식으로 name 파라미터와 함께 요청 */
        httpRequest.open('GET', `/dataload/navbar?${maindataLoadInVO}` ,true);
	    /* Response Type을 Json으로 사전 정의 */
	    httpRequest.responseType = "json";
	    /* 정의된 서버에 요청을 전송 */
		httpRequest.setRequestHeader('Content-Type','application/json');
	    httpRequest.send(maindataLoadInVO);
	}
	
);
