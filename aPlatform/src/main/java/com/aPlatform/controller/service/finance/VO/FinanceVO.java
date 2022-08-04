package com.aPlatform.controller.service.finance.VO;

import lombok.Data;
@Data
public class FinanceVO
{
	/* 종목 분류 */
	private String finCode;// 6자리 종목코드
	private String finName;// 종목명
	private String exchange;// 시장 구분
	/* 검색 옵션 */
	private String finType;
	private String finUrl;
	private String finPharse;
	/* 사용자 옵션 */
	private String userId;
	private String interestYn;
	private String ctgCulNm;

}
