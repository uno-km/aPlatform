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
	<form name="dataForm" id="dataForm" onsubmit="return registerAction()">
		<button id="btn-upload" type="button" style="border: 1px solid #ddd; outline: none;">파일 추가</button>
		<input id="input_file" multiple="multiple" type="file" style="display: none;">
		<span style="font-size: 10px; color: gray;">※첨부파일은 최대 10개까지 등록이 가능합니다.</span>
		<div class="data_file_txt" id="data_file_txt" style="margin: 40px;">
			<span>첨부 파일</span>
			<br />
			<div id="articlefileChange"></div>
		</div>
		<button type="submit" style="border: 1px solid #ddd; outline: none;">전송</button>
	</form>
</div>
<br />
<br />
<h2>드래그앤 드롭</h2>
<input type="file" id="qwe" multiple>

<div class="drop-zone">또는 파일을 여기로 드래그하세요.</div>
 <script>
        
        (function() {
            
            var $file = document.getElementById("qwe")
            var dropZone = document.querySelector(".drop-zone")

            var toggleClass = function(className) {
                
                console.log("current event: " + className)

                var list = ["dragenter", "dragleave", "dragover", "drop"]

                for (var i = 0; i < list.length; i++) {
                    if (className === list[i]) {
                        dropZone.classList.add("drop-zone-" + list[i])
                    } else {
                        dropZone.classList.remove("drop-zone-" + list[i])
                    }
                }
            }
            
            var showFiles = function(files) {
                dropZone.innerHTML = ""
                for(var i = 0, len = files.length; i < len; i++) {
                    dropZone.innerHTML += "<p>" + files[i].name + "</p>"
                }
            }

            var selectFile = function(files) {
                // input file 영역에 드랍된 파일들로 대체
                $file.files = files
                showFiles($file.files)
                
            }
            
            $file.addEventListener("change", function(e) {
                showFiles(e.target.files)
            })

            // 드래그한 파일이 최초로 진입했을 때
            dropZone.addEventListener("dragenter", function(e) {
                e.stopPropagation()
                e.preventDefault()

                toggleClass("dragenter")

            })

            // 드래그한 파일이 dropZone 영역을 벗어났을 때
            dropZone.addEventListener("dragleave", function(e) {
                e.stopPropagation()
                e.preventDefault()

                toggleClass("dragleave")

            })

            // 드래그한 파일이 dropZone 영역에 머물러 있을 때
            dropZone.addEventListener("dragover", function(e) {
                e.stopPropagation()
                e.preventDefault()

                toggleClass("dragover")

            })

            // 드래그한 파일이 드랍되었을 때
            dropZone.addEventListener("drop", function(e) {
                e.preventDefault()

                toggleClass("drop")

                var files = e.dataTransfer && e.dataTransfer.files
                console.log(files)

                if (files != null) {
                    if (files.length < 1) {
                        alert("폴더 업로드 불가")
                        return
                    }
                    selectFile(files)
                } else {
                    alert("ERROR")
                }

            })

        })();

        
    </script>

<script>
	$(document).ready(
			function() {
				document.getElementById('input_file').addEventListener(
						'change', fileCheck);
			});
	/* 첨부파일로직*/
	document.getElementById('btn-upload').addEventListener('click', function() {
		document.getElementById('input_file').click();
	})
	// 파일 현재 필드 숫자 totalCount랑 비교값
	var fileCount = 0;
	// 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
	var totalCount = 10;
	// 파일 고유넘버
	var fileNum = 0;
	// 첨부파일 배열
	var content_files = new Array();
	function fileCheck(e) {
		var files = e.target.files;
		// 파일 배열 담기
		var filesArr = Array.prototype.slice.call(files);
		// 파일 개수 확인 및 제한
		if (fileCount + filesArr.length > totalCount) {
			alret('파일은 최대 ' + totalCount + '개까지 업로드 할 수 있습니다.');
			return;
		} else {
			fileCount = fileCount + filesArr.length;
		}
		// 각각의 파일 배열담기 및 기타
		filesArr
				.forEach(function(f) {
					var reader = new FileReader();
					reader.onload = function(e) {
						content_files.push(f);
						//document.getElementById('articlefileChange').append(
						$('#articlefileChange')
								.append(
										'<div id="file'
												+ fileNum
												+ '" onclick="fileDelete(\'file'
												+ fileNum
												+ '\')">'
												+ '<font style="font-size:12px">'
												+ f.name
												+ '</font>'
												+ '<img src="/img/icon_minus.png" style="width:20px; height:auto; vertical-align: middle; cursor: pointer;"/>'
												+ '<div/>');
						fileNum++;
					};
					reader.readAsDataURL(f);
					//초기화 한다.
					document.getElementById('input_file').value = '';
				});
	}
	// 파일 부분 삭제 함수
	function fileDelete(fileNum) {
		var no = fileNum.replace(/[^0-9]/g, "");
		content_files[no].is_delete = true;
		document.getElementById(fileNum).remove();
		fileCount--;
		console.log(content_files);
	}
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
<h1>${test}</h1>
<div class='FirstRowInSection'>
	<div class='SecondRecInRow' id='kospiInfo'></div>
</div>
<div class='bigPictureWrapper'>
	<div class='bigPicture'></div>
</div>


<div class='uploadDiv'>
	<input type='file' name='uploadFile' id='fileUpload' multiple>
</div>

<div class='uploadResult'>
	<ul>

	</ul>
</div>
<button id='uploadBtn'>Upload</button>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script>
	document.getElementById('fileUpload').addEventListener('change',
			uploadFiles);
	function uploadFiles(el) {
		console.log(el);
	}
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
										+ "/" + obj.uuid + "_" + obj.fileName);
								var fileLink = fileCallPath.replace(new RegExp(
										/\\/g), "/");
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