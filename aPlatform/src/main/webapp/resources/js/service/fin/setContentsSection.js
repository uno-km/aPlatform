/** 최초 finance의 메인화면을 나타내는 js이다.
 * 
 */
function setContentsSection() {
	let struct_div = `
<div class='FirstRowInSection non-drag'>
	<div class='FirstRecInRow'>
		<div class='inner_title'>
			<div class='title non-drag'>
				<span>뉴스</span>
			</div>
			<div class='Index'></div>
		</div>
		<div class='inner_detail'>
			<div class='inner_news'></div>
		</div>
		<div class='inner_title'>
			<div class='title non-drag' style='text-align: center; width: 60%'>
				<span>시가총액상위</span>
			</div>
		</div>
		<div class='inner_detail'>
			<div class='inner_rank'>
				<div class='inner_sub_detail' style="width: 40%;" id='rankDataMCName'></div>
				<div class='inner_sub_detail' style="width: 60%;" id='rankDataMCValues'></div>
			</div>
		</div>
	</div>
	<div class='SecondRecInRow' id='kospiInfo'>
		<div class='inner_title'>
			<div class='title non-drag'>
				<span>코스피</span>
			</div>
			<div class='Index'>
				<span id='kospiIndex'></span>
			</div>
		</div>
		<div class='inner_chart_selecter'>
			<div class='inner_chart_words'id='kospi_day'>1일</div>
			<div class='inner_chart_words'id='kospi_day90'>3달</div>
			<div class='inner_chart_words'id='kospi_day365'>1년</div>
			<div class='inner_chart_words'id='kospi_day1095'>3년</div>
		</div>
		<div class='inner_detail'>
			<div class='inner_image' id='kospiImage'></div>
			<div class='inner_sub_detail' id='kospiBuyer'></div>
		</div>
		<div class='inner_title'>
			<div class='title non-drag'>
				<span>코스닥</span>
			</div>
			<div class='Index'>
				<span id='kosdaqIndex'></span>
			</div>
			<div>
				<span></span>
			</div>
		</div>
		<div class='inner_chart_selecter'>
			<div class='inner_chart_words'id='kosdaq_day'>1일</div>
			<div class='inner_chart_words'id='kosdaq_day90'>3달</div>
			<div class='inner_chart_words'id='kosdaq_day365'>1년</div>
			<div class='inner_chart_words'id='kosdaq_day1095'>3년</div>
		</div>
		<div class='inner_detail'>
			<div class='inner_image' id='kosdaqImage'></div>
			<div class='inner_sub_detail' id='kosdaqBuyer'></div>
		</div>
	</div>
</div>
`;
const inputBody = document.getElementById('ContentsSectionMain');
inputBody.innerHTML = struct_div;
}