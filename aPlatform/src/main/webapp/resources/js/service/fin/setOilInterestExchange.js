/**
 * 
 */
function getOilInterestExchange() {
    const sendingVO = {
            "url" : "main"
        ,   "pharseType" : "etcIndex"
        }
	$.ajax({
		type: 'GET',
		url: '/service/finance/etcIndex?',
		data: sendingVO,
		dataType: 'JSON', 
		async: true,
		contentType: 'application/json; charset=utf-8',
		success: function (data) {
		console.log(data);
	},
		error: function () {
		alert('통신실패!!');
	}
	});
}