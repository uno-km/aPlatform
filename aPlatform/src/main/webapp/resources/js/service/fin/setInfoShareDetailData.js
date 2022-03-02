/**
 * 
 */
function setInfoShareDetailData(data) {
	let struct_div = ``;
	//연간, 분기년월 출력
	struct_div +='<th scope="col"></th>';
	for(let day = 0 ; day <10;day++) {
		if(day==3) {
			struct_div += `<td class='info_detail_expect'>${data[1][day]}</td>`;
		}else if(day==9){
			struct_div += `<td class='info_detail_expect'>${data[1][day]}</td>`;
		}else {
			struct_div += `<td>${data[1][day]}</td>`;
		}
	}
	let inputBody = document.getElementById('monthList');
	inputBody.innerHTML = struct_div;
	//세부 내용출력
	struct_div =``;
	for(let y =2 ; y <data.length;y++) {
		struct_div += `<tr>`;
		struct_div += `<th scope="row">${data[0][y-2]}</th>`;
		for(let x = 0 ; x<10;x++) {
			if(x==3) {
					struct_div += `<td class='info_detail_expect'>${data[y][x]}</td>`;
			}else if(x==9){
					struct_div += `<td class='info_detail_expect'>${data[y][x]}</td>`;
			}else {
				struct_div += `<td>${data[y][x]}</td>`;
			}
		}
		struct_div += `</tr>`;
	}
	inputBody = document.getElementById('infoShareDetailData');
	inputBody.innerHTML = struct_div;
}