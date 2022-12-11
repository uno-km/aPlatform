window.addEventListener('load', addEvent);

function addEvent()
{
		document.getElementById('deleteHeader').addEventListener('click', _deleteAjaxHeader);
		document.getElementById('deletePathVal').addEventListener('click', _deleteAjaxPath);
		document.getElementById('deleteBody').addEventListener('click', _deleteAjaxBody);
		document.getElementById('deleteParam').addEventListener('click', _deleteAjaxParam);
		document.getElementById('get').addEventListener('click', _get);
		document.getElementById('post').addEventListener('click', _post);
		document.getElementById('put').addEventListener('click', _put);
		document.getElementById('patch').addEventListener('click', _patch);
}

var inData = { 
	'name' : 'kimeunho' 
	, TestVO : {
		age : '29'
		, gender : 'male'
	}
};
function _deleteAjaxHeader()
{
	$.ajax({
  		type: "delete"
  		, url: "header"
  		, headers  :  inData
  		, success: function(){}
	});  
}
function _deleteAjaxParam()
{
	let paramData = new URLSearchParams(inData).toString();
	$.ajax({
  		type: "delete"
  		, url: "param?" + paramData
  		, success: function(){}
	});  
}
function _deleteAjaxBody()
{
	$.ajax({
  		type: "delete"
  		, url: "body"
  		, data: inData
  		//, dataType: ""  
  		, success: function(){}
	});  
}
function _deleteAjaxPath()
{
	$.ajax({
  		type: "delete"
  		, url: "delete/kimeunho"
  		, success: function(){}
	});  
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
	$.ajax({
  		type: "post"
  		, url: "file"
		,processData : false
		,contentType : false
  		, data : inData
		,dataType : 'json'
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
  		, data : inData
		,dataType : 'json'
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
  		, data : inData
		,dataType : 'json'
  		, success: function(){}
	});  
}
