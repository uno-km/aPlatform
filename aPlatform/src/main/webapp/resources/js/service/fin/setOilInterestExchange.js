;
function getOilInterestExchange() {
    const sendingVO = {
            url : "main"
        ,   pharseType : "etcIndex"
        }
    AJAX('POST','/service/finance/data/etcIndex',sendingVO,true,function(data){
		console.log(data);
		setExchange(data);
    },null);
}
function setExchange(data){
	let struct_div = ``;
	for(let i =0 ; i < data.exChange.length;i++) {
		if(data.exChange[i+2]=="상승") {
			struct_div += `<div class='inner_etcIndex up'>${data.exChange[i]} ${data.exChange[i+1]} ${data.exChange[i+3]}</div>`;
		}else {
			struct_div += `<div class='inner_etcIndex down'>${data.exChange[i]} ${data.exChange[i+1]} ${data.exChange[i+3]}</div>`;
		}
		i+=3;
	}
	document.getElementById('exChange').innerHTML = struct_div;
	
}