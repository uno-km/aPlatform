/**
 * 
 */

function getRankdata() {
	let outData='';
    const sendingVO = {
            "url" : "main"
        ,   "pharseType" : "rankMC"  
        }
	$.ajax({
		type: 'GET',
		url: '/service/finance/rank?',
		data: sendingVO,
		dataType: 'JSON', 
		async: false,
		contentType: 'application/json; charset=utf-8',
		success: function (data) {
		outData=data;
		rankDataMC =outData;
		setRankDataMC();
		setRankDataMCColor();
	},
		error: function () {
		alert('통신실패!!');
	}
	});
}
function setRankDataMC() {
	let struct_div ="";
	let cntMax = Object.keys(this.rankDataMC).length;
	if(this.rankDataMC!=null) {
		for(let i=0;i<cntMax;i++) {// onclick='goShareInfo(this)'
			struct_div +=`
					<div class="inner_rank_m" onclick='goShareInfo(this)'>${this.rankDataMC[i][0]}</div>
						`;
		}
		document.getElementById('rankDataMCName').innerHTML=struct_div;
		struct_div =``;
		for(let i=0;i<cntMax;i++) {
			struct_div +=`
					<div class='inner_rank_values'>
						<div class='inner_rank_lin'>${this.rankDataMC[i][1]}</div>
						<div class='inner_rank_lin'>${this.rankDataMC[i][3]}</div>
						<div class='inner_rank_lin'>${this.rankDataMC[i][4]}</div>
					</div>
					<input type='hidden' name='updownChecker' value='${this.rankDataMC[i][2]}'>
						`;
		}	
		document.getElementById('rankDataMCValues').innerHTML=struct_div;
	}else {
		console.log("해당 영억없음");
	}
}
function  setRankDataMCColor() {
	for(let i=0;i<document.getElementsByClassName('inner_rank_values').length;i++) {
		if(document.getElementsByName('updownChecker')[i].value=='상승') {
			document.getElementsByClassName('inner_rank_m')[i].style.color='red';
			document.getElementsByClassName('inner_rank_m')[i].style.borderColor='red';
			document.getElementsByClassName('inner_rank_values')[i].style.color='red';
			document.getElementsByClassName('inner_rank_values')[i].style.borderColor='red';
		}else if (document.getElementsByName('updownChecker')[i].value=='0'){
			document.getElementsByClassName('inner_rank_m')[i].style.color='gray';
			document.getElementsByClassName('inner_rank_m')[i].style.borderColor='gray';
			document.getElementsByClassName('inner_rank_values')[i].style.color='gray';
			document.getElementsByClassName('inner_rank_values')[i].style.borderColor='gray';
		}
	}
}