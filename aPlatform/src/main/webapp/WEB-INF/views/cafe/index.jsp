<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<button id='post'>포스트</button>
	<input type='text' id=postText>
	<button id='postData'>포스트데이타</button>
	<button id='get'>겟</button>
	<input type='text' id=getText>
	<button id='getData'>겟데이터</button>
	<button id='put'>풋</button>
	<button id='deletefn'>딜리트</button>

	<h1>AJAX GET TEST</h1>
	Name:
	<input type="text" id="inputName"></input>
	<button id="ajaxCall">호출</button>
	<!-- 응답 받은 name 및 age 데이터를 추가해서 작성할 간단한 결과 텍스트 -->
	<h2>
		<span id="name"></span>
		의 나이는
		<span id="age"></span>
		입니다.
	</h2>
<!-- 	<h1>AJAX POST TEST</h1> -->
<!-- 	Name: -->
<!-- 	<input type="text" id="inputName"></input> -->
<!-- 	<button id="ajaxCall">호출</button> -->
<!-- 	<!-- 응답 받은 name 및 age 데이터를 추가해서 작성할 간단한 결과 텍스트 --> -->
<!-- 	<h2> -->
<!-- 		<span id="name"></span> -->
<!-- 		의 나이는 -->
<!-- 		<span id="age"></span> -->
<!-- 		입니다. -->
<!-- 	</h2> -->
	<script type="text/javascript">
	document.getElementById('post').addEventListener('click',post);
	document.getElementById('post').addEventListener('click',postData);
	document.getElementById('get').addEventListener('click',get);
	document.getElementById('getData').addEventListener('click',getData);
	document.getElementById('post').addEventListener('click',put);
	document.getElementById('post').addEventListener('click',deletefn);
	function post(){
		console.log('포스트');
		var httpRequest;
		var indata=
			{
				'qwe':'qwe'
			};
		httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = () => {
			if (httpRequest.readyState === XMLHttpRequest.DONE) {
				if (httpRequest.status === 200) {
					var result = httpRequest.response;
						alert('통신완료');
// 					    this.codeInfo = JSON.stringify(result);
// 					    let objData = JSON.stringify(result);
// 					    localStorage.setItem('sharesInfo' ,result);
					} else {
						alert('Request Error!');
					}
			    }
		};
		/* Get 방식으로 name 파라미터와 함께 요청 */
		httpRequest.open('POST', '/service/cafe/post',true);
		/* Response Type을 Json으로 사전 정의 */
		httpRequest.responseType = "json";
		httpRequest.setRequestHeader('Content-Type', 'application/json');
		/* 정의된 서버에 요청을 전송 */
		httpRequest.send(JSON.stringify(indata));
	}
	function getData(){
		console.log('포스트');
		var httpRequest;
		var indata=
			{
				'qwe':'qwe'
			};
		httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = () => {
			if (httpRequest.readyState === XMLHttpRequest.DONE) {
				if (httpRequest.status === 200) {
					var result = httpRequest.response;
						alert('통신완료');
// 					    this.codeInfo = JSON.stringify(result);
// 					    let objData = JSON.stringify(result);
// 					    localStorage.setItem('sharesInfo' ,result);
					} else {
						alert('Request Error!');
					}
			    }
		};
		/* Get 방식으로 name 파라미터와 함께 요청 */
		httpRequest.open('GET', '/service/cafe/getData',true);
		/* Response Type을 Json으로 사전 정의 */
		httpRequest.responseType = "json";
// 		httpRequest.setRequestHeader('Content-Type', 'application/json');
		/* 정의된 서버에 요청을 전송 */
		httpRequest.send(indata);
	}
	function get(){  
		var data = document.getElementById('getText').value;
		$.ajax({
        type: 'GET', //post 방식으로 전송
        url: '/service/cafe/get?data='+data, //데이터를 주고받을 파일 주소
        dataType: 'JSON', 
        async: false,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
        	console.log(data);
        },
        error: function () {
            alert('통신실패!!');
        }
    });
// 		console.log('포스트');
		var httpRequest;
		
		httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = () => {
			if (httpRequest.readyState === XMLHttpRequest.DONE) {
				if (httpRequest.status === 200) {
					var result = httpRequest.response;
						alert('통신완료');
// 					    this.codeInfo = JSON.stringify(result);
// 					    let objData = JSON.stringify(result);
// 					    localStorage.setItem('sharesInfo' ,result);
					} else {
						alert('Request Error!');
					}
			    }
		};
		/* Get 방식으로 name 파라미터와 함께 요청 */
		httpRequest.open('GET', '/service/cafe/get?data='+data,true);
		/* Response Type을 Json으로 사전 정의 */
		httpRequest.responseType = "json";
		/* 정의된 서버에 요청을 전송 */
		httpRequest.send();
	}
	window.onload = function() {
		var httpRequest;
		/* button이 클릭되었을때 이벤트 */
		document.getElementById("ajaxCall").addEventListener('click', () => {
			/* textBox에 작성된 name 데이터를 가져옴 */
			var inputName = document.getElementById("inputName").value;
			/* 통신에 사용 될 XMLHttpRequest 객체 정의 */
			httpRequest = new XMLHttpRequest();
			/* httpRequest의 readyState가 변화했을때 함수 실행 */
		    httpRequest.onreadystatechange = () => {
		    	/* readyState가 Done이고 응답 값이 200일 때, 받아온 response로 name과 age를 그려줌 */
			    if (httpRequest.readyState === XMLHttpRequest.DONE) {
				      if (httpRequest.status === 200) {
				    	var result = httpRequest.response;
				        document.getElementById("name").innerText = result.name;
				        document.getElementById("age").innerText = result.age;
				      } else {
				        alert('Request Error!');
				      }
			    }
		    };
		    /* Get 방식으로 name 파라미터와 함께 요청 */
		    httpRequest.open('GET', '/service/cafe/getAgeByName?inputName=' + inputName);
		    /* Response Type을 Json으로 사전 정의 */
		    httpRequest.responseType = "json";
		    /* 정의된 서버에 요청을 전송 */
		    httpRequest.send();
		});
	}
// 	window.onload = function() {
// 		var httpRequest;
// 		/* button이 클릭되었을때 이벤트 */
// 		document.getElementById("ajaxCall").addEventListener('click', () => {
// 			/* textBox에 작성된 name 데이터를 가져옴 */
// 			var inputName = document.getElementById("inputName").value;
// 			/* 입력된 데이터 Json 형식으로 변경 */
// 			var reqJson = new Object();
// 			reqJson.name = inputName;
// 			/* 통신에 사용 될 XMLHttpRequest 객체 정의 */
// 			httpRequest = new XMLHttpRequest();
// 			/* httpRequest의 readyState가 변화했을때 함수 실행 */
// 		    httpRequest.onreadystatechange = () => {
// 		    	/* readyState가 Done이고 응답 값이 200일 때, 받아온 response로 name과 age를 그려줌 */
// 			    if (httpRequest.readyState === XMLHttpRequest.DONE) {
// 				      if (httpRequest.status === 200) {
// 				    	var result = httpRequest.response;
// 				        document.getElementById("name").innerText = result.name;
// 				        document.getElementById("age").innerText = result.age;
// 				      } else {
// 				        alert('request에 뭔가 문제가 있어요.');
// 				      }
// 				}
// 		    };
// 		    /* Post 방식으로 요청 */
// 		    httpRequest.open('POST', '/service/cafe/postAgeByName', true);
// 		    /* Response Type을 Json으로 사전 정의 */
// 		    httpRequest.responseType = "json";
// 		    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
// 		    httpRequest.setRequestHeader('Content-Type', 'application/json');
// 		    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
// 		    httpRequest.send(JSON.stringify(reqJson));
			
// 		});
// 	}
	</script>
</body>
</html>