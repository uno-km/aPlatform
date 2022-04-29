/**
 * 
 */
function getOilInterestExchange(value) {
    const sendingVO = {
            "url" : "etcIndex"
        ,   "pharseType" : ""
        ,	"value" : `${value}`
        }
	$.ajax({
		type: 'GET',
		url: '/service/finance/etcIndex?',
		data: sendingVO,
		dataType: 'JSON', 
		async: true,
		contentType: 'application/json; charset=utf-8',
		success: function (data) {
		
	},
		error: function () {
		alert('통신실패!!');
	}
	});
}