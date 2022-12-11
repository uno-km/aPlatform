window.addEventListener('load', addEventFile);

function addEventFile()
{
		//document.getElementById('get').addEventListener('click', _get);
		document.getElementById('post').addEventListener('click', _post);
		document.getElementById('put').addEventListener('click', _put);
		document.getElementById('patch').addEventListener('click', _patch);
}
function _get()
{
	let inFile = document.getElementById('getFile').files[0];
	let inData = new FormData();
	inData.append('file', inFile);
	$.ajax({
  		type: "get"
  		, url: "file"
  		, data : inData
  		, success: function(){}
	});  
}
function _post()
{
	let inFile = document.getElementById('postFile').files[0];
	let inData = new FormData();
	inData.append('file', inFile);
	inData.append('auth', 'master');
	inData.append('type', 'manual');
	$.ajax({
  		type: "post"
  		, url: "filePart"
		, processData : false
		, contentType : false
  		, data : inData
		, dataType : 'JSON'
  		, success: function(){}
	});  
}
function _put()
{
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
