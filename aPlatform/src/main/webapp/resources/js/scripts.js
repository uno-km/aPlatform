/*!
* Start Bootstrap - Landing Page v6.0.4 (https://startbootstrap.com/theme/landing-page)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-landing-page/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
//loading
function loading(){
	LoadingWithMask('your site\'s image path');
    setTimeout("closeLoadingWithMask()", 3000);

}

function LoadingWithMask(gif) {
    var maskHeight = $(document).height();
    var maskWidth  = window.document.body.clientWidth;
     
    var mask       ="<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
    var loadingImg ='';
      
    loadingImg +=" <img src='"+ gif +"' style='position: absolute; display: block; margin: 0px auto;'/>";
 
    $('body').append(mask)
 
    $('#mask').css({
            'width' : maskWidth,
            'height': maskHeight,
            'opacity' :'0.3'
    });
  
    $('#mask').show();
  
    $('#loadingImg').append(loadingImg);
    $('#loadingImg').show();
}


function closeLoadingWithMask() {
    $('#mask, #loadingImg').hide();
    $('#mask, #loadingImg').remove(); 
}


