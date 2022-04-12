package com.aPlatform.controller.service.finance.SO;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.BO.FinanceDetailBO;
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.model.GetURLInfo;
@Service
@SuppressWarnings("unchecked")
public class FinanceSearchBOC
{
	@Autowired
	FinanceDetailBO financeDetailBO;
	@Autowired
	GetURLInfo getUrlInfo;
	@Autowired
	FinanceDataMatrix financeDataMatrix;

	public Object getInfo(String dataform, Map<String, String> map) throws Exception
	{
		switch (dataform) {
			case "kospi" :/* 코스피 */
				this.financeDataMatrix.setPageDOC("kospi");
				ArrayList<Map<String, String>> kospiArr = new ArrayList<Map<String, String>>();
				kospiArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "index"));
				kospiArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "buyer"));
				kospiArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "image"));
				return kospiArr;
			case "kosdaq" :/* 코스피 */
				this.financeDataMatrix.setPageDOC("kosdaq");
				ArrayList<Map<String, String>> kosdaqArr = new ArrayList<Map<String, String>>();
				kosdaqArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "index"));
				kosdaqArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "buyer"));
				kosdaqArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "image"));
				return kosdaqArr;
			case "total" : /* 전부 */
				this.financeDataMatrix.setPageDOC();
				ArrayList<Map<String, String>> totalArr = new ArrayList<Map<String, String>>();
				totalArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "index"));
				totalArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "buyer"));
				totalArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "image"));
				totalArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "index"));
				totalArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "buyer"));
				totalArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "image"));
				return totalArr;
			default :
				String url = map.get("url");
				String pharseType = map.get("pharseType");
				this.financeDataMatrix.setSearchUrlPharseType(map);
				this.financeDataMatrix.setPageDOC(url);
				return getUrlInfo.getUrlInfoObject(this.financeDataMatrix, url, pharseType);
		}
	}
}
