window.onload = function(){
	load_service_list;
	

	document.getElementById("loading").style.visibility="hidden";
	document.getElementById("loading_window").style.visibility="hidden";
	
}


function load_service_list(){
	ajax;
}

function ajax(){
	// XMLHttpRequest 객체의 인스턴스를 생성합니다.
	var xhr = new XMLHttpRequest();
	
	// open() 메서드는 요청을 준비하는 메서드입니다. (http 메서드, 데이터를 받아올 URL 경로, 비동기 여부)
	xhr.open("POST", "/loadservice/serviceList", true);
	// send() 메서드는 준비된 요청을 서버로 전송하는 메서드입니다. (서버에 전달될 정보)
	xhr.send();;
}