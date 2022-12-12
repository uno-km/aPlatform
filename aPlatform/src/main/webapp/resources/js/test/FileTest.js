window.addEventListener('load', addEventFile);

function addEventFile()
{
		//document.getElementById('get').addEventListener('click', _get);
		document.getElementById('post').addEventListener('click', _post);
		//document.getElementById('put').addEventListener('click', _put);
		//document.getElementById('patch').addEventListener('click', _patch);
}
function _get()
{
	let inData = {
		name	:	'unokim'
		,age	:	28
	}
	let param = new URLSearchParams(inData).toString();
	fetch("fetch?" + param)
	.then((rs) => {
		return rs.json()
	})
	.then((data)=>{
		console.log(data);
	});
}
function _post()
{
	let inFile = document.getElementById('postFile').files[0];
	let inData = new FormData();
	
	inData.append('auth', 'manager');
	inData.append('type', 'manual');
	inData.append('targetSystem', 'naver-map');
	inData.append('file', inFile);
		
	fetch(	'filePart'
			, {
				method	: "POST"
				,body	: inData
				,headers: {}
	})
	.then((rs) => {
		if(rs.ok)
		{
			console.log('통신성공');
			return rs.json()
		}
	})
	.then((data) => {
		if(data.toString()==="200")
		{
			alert("파일 업로드에 성공했습니다.");
		}
		else
		{
			alert("실패");
		}
	});
}
function _put()
{
inData.append('file', inFile);


	let inFile = document.getElementById('putFile').files[0];
	let inData = new FormData();
	inData.append('file', inFile);
	$.ajax({
  		type: "put"
  		, url: "file"
		, processData : false
		, contentType : false
  		, data : inData
		, dataType : 'JSON'
  		, success: function(){}
	});  
}
function _patch()
{
	let inFile = document.getElementById('patchFile').files[0];
	let inData = new FormData();
	inData.append('file', inFile);
	$.ajax({
  		type: "patch"
  		, url: "file"
		, processData : false
		, contentType : false
  		, data : inData
		, dataType : 'JSON'
  		, success: function(){}
	});   
}
