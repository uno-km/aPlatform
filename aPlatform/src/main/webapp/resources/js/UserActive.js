var btn = document.getElementById("loginButton"); 
var result ="";
var httpRequest = new XMLHttpRequest();


    	btn.addEventListener('click', () => {
	    var	user_id = document.getElementById("sideRemoteId").value;
        var user_password = document.getElementById("sideRemoteId").value;
		var maindataLoadInVO = {
			"user_id" : user_id,
            "user_password" : user_password
		}
        CommonAjax.gfn_SetUrl(`/user/signin.json?${Utils.gfn_ObjectQueryString(maindataLoadInVO)}`);
		CommonAjax.fn_SetType("GET")
		CommonAjax.fn_SetCallBack((data)=> {
			console.log("json연동 성공");
			try{
				console.log(data);
				setNavbar(data);
			}catch(e){
				console.log("json연동 실패");
			}
		});
		CommonAjax.fn_Ajax(null, null, true, 'VO');
	}
);
var signin = function(data){
    
}

var setNavbar = function(data){
	var navbarList = "";
	var navbarOutVO = data.navbaroutVO;

	if(navbarOutVO.length >0){
		navbarList = "<ul class='nav justify-content-center'>";
		for(var i = 0;i<navbarOutVO.length;i++){
			navbarList += "<li class='nav-item'>";
			navbarList += "		<a class='nav-link active' aria-current='page' href='#'>" +navbarOutVO[i].tmplt_id+"</a>";
			navbarList += "</li>";
		}
		navbarList += "</ul>";
	}
	const inputBody = document.getElementById('servicesNavbar');
	inputBody.innerHTML = navbarList;
}
