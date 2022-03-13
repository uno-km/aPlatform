<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/includes/common.jsp"%>
<%@ include file="/WEB-INF/includes/header.jsp"%>
<%@ include file="/WEB-INF/views/modules/loading.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>aPlatform 웹뷰 테스트</title>
</head>
<h1>테스트화면입니다.</h1>
<div class="container">
	<h2>파일업로드</h2>
	<div class="filebox">
		<label for="input_file">업로드</label>
		<input type="file" id="input_file" class="upload-hidden" multiple="multiple">
	</div>
	<span style="font-size: 10px; color: gray;">※첨부파일은 최대 10개까지 등록이 가능합니다.</span>
	<div class="data_file_txt" id="data_file_txt" style="margin: 40px;">
		<span>첨부 파일</span>
		<br />
		<div id="articlefileChange" class="drop-zone">버튼을 눌러 추가 또는 파일을 여기로 드래그하세요.</div>
	</div>
</div>3
<br />
<br />
<h2>드래그앤 드롭</h2>
<script>
document.getElementById('input_file').addEventListener('change', clickButton);
var fileCount = 0;
var totalCount = 10;
var fileNum = 0;
var dropZone = document.querySelector(".drop-zone");
var content_files = new Array();
function clickButton(e){
	var files = e.target.files;
	fileCheck(files);
}
function dropFiles(e){
	var files = e.dataTransfer && e.dataTransfer.files;
	fileCheck(files);
}
function fileCheck(files) {
	var filesArr = Array.prototype.slice.call(files);
	if (fileCount + filesArr.length > totalCount) {
		alert('파일은 최대 ' + totalCount + '개까지 업로드 할 수 있습니다.');
		return;
	} else {
		fileCount = fileCount + filesArr.length;
	}
	filesArr.forEach(function(f) {
	var reader = new FileReader();
		reader.onload = function(e) {
			content_files.push(f);
			$('#articlefileChange').append('<div id="file'+ fileNum+ '" onclick="fileDelete(\'file'+ fileNum+ '\')">'
				+ '<font style="font-size:12px">'+ f.name+ '</font>'+'<div>x</div>'+ '<div/>');
			fileNum++;
		};
		reader.readAsDataURL(f);
		document.getElementById('input_file').value = '';
	});
}
function toggleClass(className) {
	var list = [ "dragenter", "dragleave", "dragover", "drop" ];
	for (var i = 0; i < list.length; i++) {
		if (className === list[i]) {
			dropZone.classList.add("drop-zone-" + list[i]);
		} else {
			dropZone.classList.remove("drop-zone-" + list[i])
		}
	}
}
// 드래그한 파일이 최초로 진입했을 때
dropZone.addEventListener("dragenter", drageEventHandler);
// 드래그한 파일이 dropZone 영역을 벗어났을 때
dropZone.addEventListener("dragleave", drageEventHandler);
// 드래그한 파일이 dropZone 영역에 머물러 있을 때
dropZone.addEventListener("dragover", drageEventHandler);
// 드래그한 파일이 드랍되었을 때
dropZone.addEventListener("drop", function(e) {
	drageEventHandler(e);
	dropFiles(e);
});
function drageEventHandler(e){
	e.stopPropagation();
	e.preventDefault();
	toggleClass(e.type);
}
//파일 부분 삭제 함수
function fileDelete(fileNum) {
	var no = fileNum.replace(/[^0-9]/g, "");
	content_files[no].is_delete = true;
	document.getElementById(fileNum).remove();
	fileCount--;
}
</script>
<h1>${test}</h1>
</body>
</html>
<style>
.drop-zone {
	width: 500px;
	height: 500px;
	background-color: azure
}

.drop-zone-dragenter, .drop-zone-dragover {
	border: 10px solid blue;
}
</style>
<second-body>

<style>
.filebox label {
	display: inline-block;
	padding: .5em .75em;
	color: #999;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #fdfdfd;
	cursor: pointer;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
}

.filebox input[type="file"] { /* 파일 필드 숨기기 */
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}
/* named upload */
.filebox .upload-name {
	display: inline-block;
	padding: .5em .75em; /* label의 패딩값과 일치 */
	font-size: inherit;
	font-family: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #f5f5f5;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
	-webkit-appearance: none; /* 네이티브 외형 감추기 */
	-moz-appearance: none;
	appearance: none;
}
</style>
<script>

/*폼 submit 로직 */
function registerAction() {
	var form = $("form")[0];
	var formData = new FormData(form);
	for (var x = 0; x < content_files.length; x++) {
		// 삭제 안한것만 담아 준다. 
		if (!content_files[x].is_delete) {
			formData.append("article_file", content_files[x]);
		}
	}
	/*
	 * 파일업로드 multiple ajax처리
	 */
	$.ajax({
		type : "POST",
		enctype : "multipart/form-data",
		url : "/file-upload",
		data : formData,
		processData : false,
		contentType : false,
		success : function(data) {
			if (JSON.parse(data)['result'] == "OK") {
				alert("파일업로드 성공");
			} else
				alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
		},
		error : function(xhr, status, error) {
			alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
			return false;
		}
	});
	return false;
}
</script>