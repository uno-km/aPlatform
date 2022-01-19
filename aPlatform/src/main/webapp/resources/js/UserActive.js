var btn = document.getElementById("loginButton");
var result = "";
var httpRequest = new XMLHttpRequest();


$(document).ready(function () {
    if(!localStorage.length<1){
        callNavBar();
    }


    btn.addEventListener('click', () => {
      var user_id = document.getElementById("sideRemoteId").value;
      var user_password = document.getElementById("sideRemoteId").value;
      var maindataLoadInVO = {
          "user_id": user_id,
          "user_password": user_password
      }
      $.ajax({
          type: 'POST', //post 방식으로 전송
          url: '/user/signin', //데이터를 주고받을 파일 주소
          data: JSON.stringify(maindataLoadInVO), //위의 변수에 담긴 데이터를 전송해준다.
          dataType: 'JSON', 
          async : false,
          contentType: 'application/json; charset=utf-8',
          success: function (data) {
              setSession(data);
              callNavBar();
              signin(data);
          },
          error: function () {
              alert('통신실패!!');
          }
      });
  });
});

// $(document).ready(function () {
//     btn.addEventListener('click', () => {
//         var user_id = document
//             .getElementById("sideRemoteId")
//             .value;
//         var user_password = document
//             .getElementById("sideRemoteId")
//             .value;
//         var maindataLoadInVO = {
//             "user_id": user_id,
//             "user_password": user_password
//         }
//         CommonAjax.gfn_SetUrl(`/user/signin`);
//         CommonAjax.fn_SetType("POST");
//         CommonAjax.fn_SetCallBack((data) => {
//             console.log("json연동 성공");
//             try {
//                 console.log(data);
//                 setNavbar(data);
//             } catch (e) {
//                 console.log("json연동 실패");
//             }
//         });
//         CommonAjax.fn_Ajax(null, null, true, 'VO');
//     });
// });
var setSession = function(data){
    localStorage.clear
    localStorage.setItem('user_auth', data.user_auth);
    localStorage.setItem('user_birth', data.user_birth );
    localStorage.setItem('user_email',  data.user_email);
    localStorage.setItem('user_id',  data.user_id);
    localStorage.setItem('user_name', data.user_name);
    localStorage.setItem('user_password',  data.user_password);
    localStorage.setItem('user_phonenum',  data.user_phonenum);
}

var signin = function (data) {
}
var callNavBar = function () {
    const here_user_id = localStorage.getItem('user_id');
    
    var navbaroutVO = {
        "user_id":  here_user_id
    }
    $.ajax({
        type:'POST',
        url: '/dataload/navbar',
        data: JSON.stringify(navbaroutVO),
        dataType: 'JSON', 
        async : false,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            setNavbar(data);
        },
        error: function () {
            alert('통신실패!!');
        }
    });
}
var setNavbar = function (data) {
    var navbarList = "";
    var navbarOutVO = data.navbaroutVO;
    var jbBtn =document.createElement( 'button' );
    var jbBtnText = document.createTextNode( 'Click' );
    jbBtn.appendChild( jbBtnText );
    document.body.appendChild( jbBtn );

    if (navbarOutVO.length > 0) {
        navbarList = "<c:if test=`${not empty sessionScope.user_id}`>";
        navbarList +=   "<ul class='nav justify-content-center'>";
        for (var i = 0; i < navbarOutVO.length; i++) {
            navbarList += "<li class='nav-item'>";
            navbarList += "		<a class='nav-link active' aria-current='page' href='#'>" +
                    navbarOutVO[i].tmplt_id + "</a>";
            navbarList += "</li>";
        }
        navbarList +=   "</ul>";
        navbarList += "</c:if>";
        
    }
    const inputBody = document.getElementById('servicesNavbar');
    inputBody.innerHTML = navbarList;
}

