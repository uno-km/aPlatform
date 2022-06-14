package com.aPlatform.controller.service.finance.VO;

import java.util.Map;

import lombok.Data;

@Data
public class FinanceVO
{
	public FinanceVO()
	{

	}
	public FinanceVO(final Map<String, String> param)
	{
		for (int i = 0; i < param.size(); i++)
		{
			for (int j = 0; j < finArr.length; j++)
			{
				if(param.containsKey(finArr[j]))
				{
					switch (finArr[j]) {
						case "finCode" :
							this.setFinCode(param.get(finArr[j]));
							break;
						case "finName" :
							this.setFinName(param.get(finArr[j]));
							break;
						case "exchange" :
							this.setExchange(param.get(finArr[j]));
							break;
						case "finType" :
							this.setFinType(param.get(finArr[j]));
							break;
						case "finUrl" :
							this.setFinUrl(param.get(finArr[j]));
							break;
						case "finPharse" :
							this.setFinPharse(param.get(finArr[j]));
							break;
						case "userId" :
							this.setUserId(param.get(finArr[j]));
							break;
						case "interestYn" :
							this.setInterestYn(param.get(finArr[j]));
							break;
					}
				}
			}
		}
	}
	private String[] finArr = {"finCode" , "finName" , "exchange" , "finType" , "finUrl" , "finPharse" , "userId" , "interestYn" };
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

}
