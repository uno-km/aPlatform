document.write("<script src='/resources/lib/html2canvas.js'></script>");
document.write("<script src='/resources/lib/jspdf.min.js'></script>");
//^^PDF생성 라이브러리 파일
/**
 * 
 */
function setSessionSharesInfo() {
	let outData ="";
    $.ajax({
        type: 'GET',
        url: '/service/finance/codeAllMap', 
        dataType: 'JSON', 
        async: false,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
    		outData=data;
        },
        error: function () {
            alert('통신실패!!');
        }
    });
    this.codeInfo = JSON.stringify(outData);
    let objData = JSON.stringify(outData);
    localStorage.setItem('sharesInfo' ,objData);
}

function AJAX(TYPE_,URL_,DATA_,ASYNC_,fn1 ,fn2) {
	let inTYPE_ = TYPE_;
	let inURL_ = URL_;
	let inDATA_ = DATA_;
	let inASYNC_ = ASYNC_;
	if(inTYPE_==null || inTYPE_ ==undefined) {
		TYPE_="GET"
	}
	if(inURL_==null || inURL_==undefined) {
		inURL_ = "error/404"
	}
	if(inDATA_==null || inDATA_==undefined) {
		inDATA_ = null;
	}
	if(inASYNC_==null || inASYNC_==undefined) {
		inASYNC_ = false;
	}
	$.ajax({
        type: inTYPE_,
        url: inURL_,
        data : inDATA_,
        dataType: 'JSON', 
        async: inASYNC_,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
			if(fn1!=null||fn1!=undefined) {
				fn1(data)
			}
        },
        error: function () {
            alert('통신실패!!');
        }
    });
	if(fn2!=null||fn2!=undefined) {
		infn2
	}
}