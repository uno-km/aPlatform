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
<body>
	<h1>테스트화면입니다.</h1>
	<h1>${test}</h1>
	<div class='FirstRowInSection'>
		<div class='SecondRecInRow' id='kospiInfo'></div>
	</div>
	<div class='bigPictureWrapper'>
		<div class='bigPicture'></div>
	</div>


	<div class='uploadDiv'>
		<input type='file' name='uploadFile' multiple>
	</div>

	<div class='uploadResult'>
		<ul>

		</ul>
	</div>
	<button id='uploadBtn'>Upload</button>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script>
		function showImage(fileCallPath) {
			$(".bigPictureWrapper").show();
			$(".bigPicture").html(
					"<img src='/display?fileName=" + fileCallPath + "'>");
		}
		$(".uploadResult").on("click", "span", function(e) {
			var targetFile = $(this).data("file");
			var type = $(this).data("type");
			var targetLi = $(this).closest("li");
			targetLi.remove();
			console.log(targetFile);
			$.ajax({
				url : '/deleteFile',
				data : {
					fileName : targetFile,
					type : type
				},
				dataType : 'text',
				type : 'POST',
				success : function(result) {
					alert(result);
				}
			}); //$.ajax
		});
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; //5MB
		function checkExtension(fileName, fileSize) {
			if (fileSize >= maxSize) {
				alert("파일 사이즈 초과");
				return false;
			}
			if (regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드할 수 없습니다.");
				return false;
			}
			return true;
		}
		var cloneObj = $(".uploadDiv").clone();
		$("#uploadBtn").on("click", function(e) {
			var formData = new FormData();
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			for (var i = 0; i < files.length; i++) {
				if (!checkExtension(files[i].name, files[i].size)) {
					return false;
				}
				formData.append("uploadFile", files[i]);
			}
			$.ajax({
				url : '/uploadFormAction',
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				dataType : 'json',
				success : function(result) {
					console.log(result);
					showUploadedFile(result);
					$(".uploadDiv").html(cloneObj.html());
				}
			}); //$.ajax
		});
		var uploadResult = $(".uploadResult ul");
		function showUploadedFile(uploadResultArr) {
			var str = "";
			$(uploadResultArr)
					.each(
							function(i, obj) {
								if (!obj.image) {
									var fileCallPath = encodeURIComponent(obj.uploadPath
											+ "/"
											+ obj.uuid
											+ "_"
											+ obj.fileName);
									var fileLink = fileCallPath.replace(
											new RegExp(/\\/g), "/");
									str += "<li><div><a href='/download?fileName="
											+ fileCallPath
											+ "'>"
											+ "<img src='/resources/img/attach.png'>"
											+ obj.fileName
											+ "</a>"
											+ "<span data-file=\'"+fileCallPath+"\' data-type='file'> x </span>"
											+ "<div></li>"
								} else {
									var fileCallPath = encodeURIComponent(obj.uploadPath
											+ "/thumb_"
											+ obj.uuid
											+ "_"
											+ obj.fileName);
									var originPath = obj.uploadPath + "\\"
											+ obj.uuid + "_" + obj.fileName;
									originPath = originPath.replace(new RegExp(
											/\\/g), "/");
									str += "<li><a href=\"javascript:showImage(\'"
											+ originPath
											+ "\')\">"
											+ "<img src='display?fileName="
											+ fileCallPath
											+ "'></a>"
											+ "<span data-file=\'"+fileCallPath+"\' data-type='image'> x </span>"
											+ "<li>";
								}
							});
			uploadResult.append(str);
		}
	</script>
</body>
</html>
<script>
	
</script>

