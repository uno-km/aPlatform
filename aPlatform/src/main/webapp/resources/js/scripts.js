var btn = document.getElementById("loginButton"); 
var result ="";
var httpRequest = new XMLHttpRequest();
var user_id = ""

    	btn.addEventListener('click', () => {
		user_id = document.getElementById("sideRemoteId").value;
		var maindataLoadInVO = {
			"user_id" : user_id
		}
		CommonAjax.gfn_SetUrl(`/dataload/navbar?${Utils.gfn_ObjectQueryString(maindataLoadInVO)}`);
		CommonAjax.fn_SetType("GET")
		CommonAjax.fn_SetCallBack((data)=> {
			console.log("json연동 성공");
			try{
				console.log(data);
			}catch(e){
				console.log("json연동 실패");
			}
		});
		CommonAjax.fn_Ajax(null, null, true, 'VO');
	}
);
