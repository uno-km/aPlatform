<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class='MiddleSection'>
	<div class="textInput">
		<div class="unoRow">
			<div class='col-75'>
				<div class="input-group">
					<button id='ext' type="button" class="btn btn-outline-primary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="true" hidden></button>
					<input type="text" class="form-control" id='searchShareInput' style='border: 2px solid #0d6efd;'>
					<ul class="dropdown-menu shareSearchInput" id='searchingList'>
					</ul>
				</div>
			</div>
			<div style='width: 1%'></div>
			<div class='col-15'>
				<button class="unoBnt unoBnt-bigBnt" type="button" id='searchShareBtn'>검색</button>
			</div>
			<div style='width: 1%'></div>
			<div class='col-10'>
				<button class="unoBnt unoBnt-bigBnt" type="button" id='getPDFFile'>PDF</button>
			</div>
		</div>
	</div>
</section>