var btn = document.getElementById("loginButton"); 
var result ="";
/* 통신에 사용 될 XMLHttpRequest 객체 정의 */
var httpRequest = new XMLHttpRequest();

	/* button이 클릭되었을때 이벤트 */
    	btn.addEventListener('click', () => {
		/* textBox에 작성된 name 데이터를 가져옴 */
		var inputName = document.getElementById("sideRemoteId").value;
		/* httpRequest의 readyState가 변화했을때 함수 실행 */
	    httpRequest.onreadystatechange = () => {
	    	/* readyState가 Done이고 응답 값이 200일 때, 받아온 response로 name과 age를 그려줌 */
		    // if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.readyState == 4) {
			      if (httpRequest.status === 200) {
			    	// result = httpRequest.response;
                    alert(inputName);
			      } else {
			        alert('Request Error!');
			      }
		    }
	    };
	    /* Get 방식으로 name 파라미터와 함께 요청 */
	    // httpRequest.open('GET', '/getAgeByName?inputName=' + inputName);
        httpRequest.open('GET', '/user/test' );
	    /* Response Type을 Json으로 사전 정의 */
	    httpRequest.responseType = "json";
	    /* 정의된 서버에 요청을 전송 */
	    httpRequest.send();
	});
