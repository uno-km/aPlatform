<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class='MiddleSection'>
	<div class="textInput">
		<div class="unoRow">
			<div class='col-85'>
				<div class="input-group">
					<button id='ext' type="button" class="btn btn-outline-primary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
						<span class="visually-hidden-focusable">Toggle Dropdown</span>
					</button>
					<input type="text" class="form-control" id='searchShareInput' style='border: 2px solid #0d6efd;'>
					<ul class="dropdown-menu shareSearchInput" style='transform: translate3d(0px, 58px, 0px);'> 
						<li>
							<a class="dropdown-item" href="#">Action</a>
						</li>
						<li>
							<a class="dropdown-item" href="#">Another action</a>
						</li>
						<li>
							<a class="dropdown-item" href="#">Something else here</a>
						</li>
					</ul>
				</div>
			</div>
			<div style='width: 2%'></div>
			<div class='col-15'>
				<button class="unoBnt" type="button" id='searchShareBtn'>검색</button>
			</div>
		</div>
	</div>
</section>