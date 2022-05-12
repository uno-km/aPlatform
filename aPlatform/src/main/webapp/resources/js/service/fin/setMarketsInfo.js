/*최초 메인메이지를 보여주는 화면*/
function getFindata(marketType) {
	let outData='';
	let sendingVO ='';
	switch(marketType) {
		case "kospi":
		    sendingVO = {"url" : "kospi"
		        	,   "pharseType" : "kospi"};
		    AJAX('GET' ,`/service/finance/${marketType}` ,sendingVO ,true ,setKospiData ,null);
			break;
		case "kosdaq" :
			sendingVO = {"url" : "kosdaq"
				,   "pharseType" : "kosdaq"};
		    AJAX('GET' ,`/service/finance/${marketType}` ,sendingVO ,true ,setKosdaqData ,null);
			break;
		case "total" :
		    AJAX('GET' ,`/service/finance/${marketType}` ,null ,true ,setTotalData ,null);
			break;
	}
	function setKospiData(outData) {
		kospiIndex	= outData[0].kospi_index;
		kospiBuyer= outData[1];
		kospiImage= outData[2];
		setKospiIndex();
		setKospiImage();
		setKospiBuyer();
		setKospiBuyerColor();
	}
	function setKosdaqData(outData) {
		kosdaqIndex= outData[0].kosdaq_index;
		kosdaqBuyer= outData[1];
		kosdaqImage= outData[2];
		setKosdaqIndex();
		setKosdaqImage();
		setKosdaqBuyer();
		setKosdaqBuyerColor();
	}
	function setTotalData(outData) {
		kospiIndex	= outData[0].kospi_index;
		kospiBuyer= outData[1];
		kospiImage= outData[2];
		kosdaqIndex= outData[3].kosdaq_index;
		kosdaqBuyer= outData[4];
		kosdaqImage= outData[5];
		nowFinData = outData;
		firstSetMarketsInfo();
	}
}
function firstSetMarketsInfo() {
	setKospiIndex();
	setKosdaqIndex();
	setKospiImage();
	setKosdaqImage();
	setKospiBuyer();
	setKospiBuyerColor();
	setKosdaqBuyer();
	setKosdaqBuyerColor();
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
	if(this.kospiBuyer!=null) {
		document.getElementById('kospiBuyer').innerHTML =`
							<div class='inner_kospiBuyer'>개인</div>
							<div class='inner_kospiBuyer'>${this.kospiBuyer.kospi_ant}</div>
							<div class='inner_kospiBuyer'>기관</div>
							<div class='inner_kospiBuyer'>${this.kospiBuyer.kospi_org}</div>
							<div class='inner_kospiBuyer'>외국인</div>
							<div class='inner_kospiBuyer'>${this.kospiBuyer.kospi_frg}</div>`;
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
	if(this.kosdaqBuyer!=null) {
		document.getElementById('kosdaqBuyer').innerHTML = `
							<div class='inner_kosdaqBuyer'>개인</div>
							<div class='inner_kosdaqBuyer'>${this.kosdaqBuyer.kosdaq_ant}</div>
							<div class='inner_kosdaqBuyer'>기관</div>
							<div class='inner_kosdaqBuyer'>${this.kosdaqBuyer.kosdaq_org}</div>
							<div class='inner_kosdaqBuyer'>외국인</div>
							<div class='inner_kosdaqBuyer'>${this.kosdaqBuyer.kosdaq_frg}</div>`;
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
