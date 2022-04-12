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
		if(dataform.contains("kospi") || dataform.contains("kosdaq") || dataform.contains("total"))
		{
			ArrayList<Map<String, String>> outArr = new ArrayList<Map<String, String>>();
			this.financeDataMatrix.setPageDOC();
			switch (dataform) {
				case "kosqi" :
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "index"));
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "buyer"));
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "image"));
					return outArr;
				case "kosdaq" :
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "index"));
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "buyer"));
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "image"));
					return outArr;
				default : /* 전부 */
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "index"));
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "buyer"));
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "image"));
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "index"));
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "buyer"));
					outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "image"));
					return outArr;
			}
		}
		else
		{
			String url = map.get("url");
			String pharseType = map.get("pharseType");
			this.financeDataMatrix.setSearchUrlPharseType(map);
			this.financeDataMatrix.setPageDOC(url);
			return getUrlInfo.getUrlInfoObject(this.financeDataMatrix, url, pharseType);
		}
	}
}
