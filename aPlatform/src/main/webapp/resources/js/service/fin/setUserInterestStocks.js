;
function setInterestStockBnt(){
	const auth = localStorage.user_auth;
	let innerStruck = document.getElementById('getStockItems');
	switch(auth) {
		case "100" :
			innerStruck.innerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="">관심종목추가</button>
			`;
			break;
		case 'ROLE_MEMBER' :
			innerStruck.innerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="">종목최신화</button>
			`;
			break;
		default :
			innerStruckinnerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="">로그인</button>
			`;
			break;
	}
}