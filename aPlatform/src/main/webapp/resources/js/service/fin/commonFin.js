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

async function AJAX(inType, inUrl,inData,inAsync,fn1 ,fn2) {
	let inTYPE_ = inType;
	let inURL_ = inUrl;
	let inDATA_ = inData;
	let inASYNC_ = inAsync;
	let inDataType= 'JSON';
	let inContextType_ = 'application/json; charset=utf-8';
	if(inURL_==='' || inURL_==null || inURL_==undefined) {
		inURL_ = "error/404"
	}
	if(!(inType === ''||inType==null || inType==undefined)) {
		if(inType=="POST") {
			inDATA_ = JSON.stringify(inData);
		}
	}else {
		inTYPE_="GET";
	}
	if(inASYNC_==null || inASYNC_==undefined) {
		inASYNC_ = false;
	}
	$.ajax({
        type: inTYPE_
        ,url: inURL_
        ,data : inDATA_
        ,dataType: inDataType 
        ,async: inASYNC_
        ,contentType: inContextType_
        ,success: function (data) {
			if(fn1!=null||fn1!=undefined) {
				fn1(data)
			}
        }
        ,error: function () {
            alert('통신실패!!');
        }
    });
	if(fn2!=null||fn2!=undefined) {
		fn2
	}
}