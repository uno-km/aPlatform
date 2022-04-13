/*뉴스데이터를 가져오는 js*/
function setNewdata(){
	let struct_div ="";
	if(this.newsData!=null) {
		for(let  i = 0; i <this.newsData.length;i++) {
			const badgood = checkBadandGood(this.newsData[i][0]);
			struct_div	+=	`<div class='inner_news_title ${badgood}'>
								<a href='https://finance.naver.com/${this.newsData[i][1]}'>${this.newsData[i][0]}</a>
							</div>`;
		}
		const inputBody = document.getElementsByClassName('inner_news')[0];
		inputBody.innerHTML=struct_div;
	}else {
		console.log("해당 영억없음");
	}
}
function getNewsdata() {
    const sendingVO = {
            "url" : "main"
        ,   "pharseType" : "news"  
        }
	let outData='';
	$.ajax({
		type: 'GET',
		url: '/service/finance/news?',
		data: sendingVO,
        dataType: 'JSON', 
        async: true,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
			outData=data;
			newsData = outData;
			setNewdata();
        },
        error: function () {
            alert('통신실패!!');
        }
    });
}
function checkBadandGood(data) {
	const bad =new RegExp("(급락|하락|붕괴|↓|약보합|하회|약세)");
	const good =  new RegExp("(급반등|반등|급상승|상승|기대|회복|↑|강보합|상회|강세)");
	if(bad.test(data)) {
		return "bad";
	}else if(good.test(data)) {
		return "good";
	}
	return;
}