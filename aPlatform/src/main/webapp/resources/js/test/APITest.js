window.addEventListener('load', addEvent);

function addEvent()
{
		document.getElementById('deleteHeader').addEventListener('click', _deleteAjaxHeader);
		document.getElementById('deletePathVal').addEventListener('click', _deleteAjaxPath);
		document.getElementById('deleteBody').addEventListener('click', _deleteAjaxBody);
		document.getElementById('deleteParam').addEventListener('click', _deleteAjaxParam);
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