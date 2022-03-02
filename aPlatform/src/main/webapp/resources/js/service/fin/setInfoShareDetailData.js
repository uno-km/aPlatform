/**
 * 
 */
function setInfoShareDetailData(data) {
	let struct_div = ``;
	for(let y =0 ; y <data.length;y++) {
		struct_div += `<tr>`;
		struct_div += `<th scope="row">${y}</th>`;
		for(let x = 0 ; x<10;x++) {
			struct_div += `<td>${data[y][x]}</td>`;
		}
		struct_div += `</tr>`;
	}
	const inputBody = document.getElementById('infoShareDetailData');
	inputBody.innerHTML = struct_div;
}