<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class='ContentsSection'>
	<div class='FirstRowInSection'>
		<div class='FirstRecInRow'>
			<div class='inner_title'>
				<div class='title'>
					<span>뉴스</span>
				</div>
				<div class='Index'></div>
			</div>
			<div class='inner_detail'>
				<div class='inner_news'></div>
			</div>
			<div class='inner_title'>
				<div class='title' style='text-align: center; width: 60%'>
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
				<div class='title'>
					<span>코스피</span>
				</div>
				<div class='Index'>
					<span id='kospiIndex'></span>
				</div>
			</div>
			<div class='inner_detail'>
				<div class='inner_image' id='kospiImage'></div>
				<div class='inner_sub_detail' id='kospiBuyer'></div>
			</div>
			<div class='inner_title'>
				<div class='title'>
					<span>코스닥</span>
				</div>
				<div class='Index'>
					<span id='kosdaqIndex'></span>
				</div>
			</div>
			<div class='inner_detail'>
				<div class='inner_image' id='kosdaqImage'></div>
				<div class='inner_sub_detail' id='kosdaqBuyer'></div>
			</div>
		</div>
	</div>
</section>