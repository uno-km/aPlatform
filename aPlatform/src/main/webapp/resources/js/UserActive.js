var btn = document.getElementById("loginButton");
var result = "";
var httpRequest = new XMLHttpRequest();


$(document).ready(function () {
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
              callNavBar(data);
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
var signin = function (data) {
}
var callNavBar = function (data) {
    var navbaroutVO = {
        "user_id":  data.user_id
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

    if (navbarOutVO.length > 0) {
        navbarList = "<ul class='nav justify-content-center'>";
        for (var i = 0; i < navbarOutVO.length; i++) {
            navbarList += "<li class='nav-item'>";
            navbarList += "		<a class='nav-link active' aria-current='page' href='#'>" +
                    navbarOutVO[i].tmplt_id + "</a>";
            navbarList += "</li>";
        }
        navbarList += "</ul>";
    }
    const inputBody = document.getElementById('servicesNavbar');
    inputBody.innerHTML = navbarList;
}

