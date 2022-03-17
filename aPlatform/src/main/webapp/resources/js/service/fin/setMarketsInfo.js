/**
 * 
 */
function getFindata() {
	let outData='';
    $.ajax({
        type: 'GET',
        url: '/service/finance/total',
        dataType: 'JSON', 
        async: false,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
    		outData=data;
        },
        error: function () {
            alert('통신실패!!');
        }
    });
	this.kospiIndex	= outData[0].kospi_index;
	this.kospiBuyer= outData[1];
	this.kospiImage= outData[2];
	this.kosdaqIndex= outData[3].kosdaq_index;
	this.kosdaqBuyer= outData[4];
	this.kosdaqImage= outData[5];
    this.nowFinData = outData;
}

function setKospiIndex() {
	const inputBody= document.getElementById('kospiIndex');
	inputBody.innerText = this.kospiIndex;
}
function setKosdaqIndex() {
	const inputBody= document.getElementById('kosdaqIndex');
	inputBody.innerText = this.kosdaqIndex;
}
function setKospiImage() {
	const inputBody= document.getElementById('kospiImage');
	inputBody.style.backgroundImage=`URL('${this.kospiImage.kospi_day}')`;
	inputBody.style.backgroundSize="100% 100%";
	inputBody.style.backgroundPosition="center";
}
function setKosdaqImage() {
	const inputBody= document.getElementById('kosdaqImage');
	inputBody.style.backgroundImage=`URL('${this.kosdaqImage.kosdaq_day}')`;
	inputBody.style.backgroundSize="100% 100%";
	inputBody.style.backgroundPosition="center";
}
function changeChart(e) {
	let imgsrc = ``;
	if(e.target.id.substring(0,e.target.id.indexOf('_'))=='kospi') {
		changeButtonClass(e);
		imgsrc = eval(`kospiImage.${e.target.id}`);
		const inputBody= document.getElementById('kospiImage');
		inputBody.style.backgroundImage=`URL('${imgsrc}')`;
		inputBody.style.backgroundSize="100% 100%";
		inputBody.style.backgroundPosition="center";
	}else {
		changeButtonClass(e);
		imgsrc = eval(`kosdaqImage.${e.target.id}`);
		const inputBody= document.getElementById('kosdaqImage');
		inputBody.style.backgroundImage=`URL('${imgsrc}')`;
		inputBody.style.backgroundSize="100% 100%";
		inputBody.style.backgroundPosition="center";
	}
}
function changeButtonClass(e) {
	let str = e.target.id.substring(0,e.target.id.indexOf('_'));
	document.getElementById(`${str}_day`).setAttribute('class','inner_chart_words');
	document.getElementById(`${str}_day90`).setAttribute('class','inner_chart_words');
	document.getElementById(`${str}_day365`).setAttribute('class','inner_chart_words');
	document.getElementById(`${str}_day1095`).setAttribute('class','inner_chart_words');
	document.getElementById(`${e.target.id}`).setAttribute('class','inner_chart_words_checked');
}
function setKospiBuyer() {
	let struct_div ="";
	if(this.kospiBuyer!=null) {
		struct_div	=	`	<div class='inner_kospiBuyer'>개인</div>
							<div class='inner_kospiBuyer'>${this.kospiBuyer.kospi_ant}</div>
							<div class='inner_kospiBuyer'>기관</div>
							<div class='inner_kospiBuyer'>${this.kospiBuyer.kospi_org}</div>
							<div class='inner_kospiBuyer'>외국인</div>
							<div class='inner_kospiBuyer'>${this.kospiBuyer.kospi_frg}</div>`;
		const inputBody = document.getElementById('kospiBuyer');
		inputBody.innerHTML=struct_div;
	}else {
        console.log("해당 영억없음");
	}
}
function setKospiBuyerColor() {
	for(let i=0;i<document.getElementsByClassName('inner_kospiBuyer').length;i++) {
		if(document.getElementsByClassName('inner_kospiBuyer')[i].innerText.includes('+')) {
			document.getElementsByClassName('inner_kospiBuyer')[i].style.color='red';
			document.getElementsByClassName('inner_kospiBuyer')[i].style.borderColor='red';
		}
	}
}
function setKosdaqBuyer() {
	let struct_div ="";
	if(this.kosdaqBuyer!=null) {
		struct_div	=	`	<div class='inner_kosdaqBuyer'>개인</div>
							<div class='inner_kosdaqBuyer'>${this.kosdaqBuyer.kosdaq_ant}</div>
							<div class='inner_kosdaqBuyer'>기관</div>
							<div class='inner_kosdaqBuyer'>${this.kosdaqBuyer.kosdaq_org}</div>
							<div class='inner_kosdaqBuyer'>외국인</div>
							<div class='inner_kosdaqBuyer'>${this.kosdaqBuyer.kosdaq_frg}</div>`;
		const inputBody = document.getElementById('kosdaqBuyer');
		inputBody.innerHTML=struct_div;
	}else {
		console.log("해당 영억없음");
	}
}
function setKosdaqBuyerColor() {
	for(let i=0;i<document.getElementsByClassName('inner_kosdaqBuyer').length;i++) {
		if(document.getElementsByClassName('inner_kosdaqBuyer')[i].innerText.includes('+')) {
			document.getElementsByClassName('inner_kosdaqBuyer')[i].style.color='red';
			document.getElementsByClassName('inner_kosdaqBuyer')[i].style.borderColor='red';
		}
	}
}
