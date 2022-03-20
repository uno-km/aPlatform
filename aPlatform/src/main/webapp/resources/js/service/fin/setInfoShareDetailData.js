/**
 * 
 */
function setInfoShareDetail(data) {
	setInfoShareDetailFrame();
	setInfoShareDetailData(data);
	setInfoShareDetailForignList();
	setInfoShareDetailOpinionList();
	setInfoShareDetailPerEpsList();
	setInfoShareDetailSichongList();
	setInfoShareDetailSameList();
}

function setInfoShareDetailFrame() {
		let struct_div = `
				<div class='ContentsSectionInfoDTL' id='ContentsSectionInfoDTL'>
					<div class='info_detail_chart'>
						<div class='info_detail_today'></div>
					</div>
					<div class='info_detail_outer'>
						<div class = 'info_detail_inner sichongList'></div>
						<div class = 'info_detail_inner perEpsList'></div>
						<div class = 'info_detail_inner forignList'></div>
						<div class = 'info_detail_inner opinionList'></div>
						<div class = 'info_detail_inner sameList'></div>
					</div>
				</div>
				<div id='ContentsSectionDTL' style='width: 100%;'>
					<table class="table table-dark table-hover" style='background-color: #394a5c00;'>
						<thead>
							<tr>
								<th scope="col" class='center_uno' style='width: 13%'>주요재무정보</th>
								<th scope="col" colspan="4" class="table-active center_uno">최근 연간 실적</th>
								<th scope="col" colspan="6" class="table-active center_uno">최근 분기 실적</th>
							</tr>
							<tr id='monthList'>
							</tr>
					</thead>
						<tbody id='infoShareDetailData'>
						</tbody>
					</table>
				</div>`;
	inputBody = document.getElementById('ContentsSectionMain');
	inputBody.innerHTML = struct_div;
}

function setInfoShareDetailData(data) {
	let struct_div = ``;
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
				if(data[y][x].length<1) {
					struct_div += `<td class='info_detail_expect'>-</td>`;
				}else if(data[y][x]>data[y][x-1]) {
					struct_div += `<td class='info_detail_expect' style='color : #ff3d3d;'>↑ ${data[y][x]}</td>`;
				}else if(data[y][x]<data[y][x-1]) {
					struct_div += `<td class='info_detail_expect' style='color : #a695ff;'>↓ ${data[y][x]}</td>`;
				}else {
					struct_div += `<td class='info_detail_expect'>${data[y][x]}</td>`;
				}
			}else if(x==9){
				if(data[y][x].length<1) {
					struct_div += `<td class='info_detail_expect'>-</td>`;
				}else if(data[y][x]>data[y][x-1]) {
					struct_div += `<td class='info_detail_expect' style='color : #ff3d3d;'>↑ ${data[y][x]}</td>`;
				}else if(data[y][x]<data[y][x-1]) {
					struct_div += `<td class='info_detail_expect' style='color : #a695ff;'>↓ ${data[y][x]}</td>`;
				}else {
					struct_div += `<td class='info_detail_expect'>${data[y][x]}</td>`;
				}
			}else {
				struct_div += `<td>${data[y][x]}</td>`;
			}
		}
		struct_div += `</tr>`;
	}
	inputBody = document.getElementById('infoShareDetailData');
	inputBody.innerHTML = struct_div;
}

function setInfoShareDetailForignList() {
	
}
function setInfoShareDetailOpinionList() {
	
}
function setInfoShareDetailPerEpsList() {
	
}
function setInfoShareDetailSichongList() {
	
}
function setInfoShareDetailSameList() {
	
}