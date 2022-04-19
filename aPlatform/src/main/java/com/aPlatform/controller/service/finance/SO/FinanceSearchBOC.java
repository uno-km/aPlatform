package com.aPlatform.controller.service.finance.SO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.model.GetURLInfo;
@Service
@SuppressWarnings("unchecked")
public class FinanceSearchBOC
{
	@Autowired
	GetURLInfo getUrlInfo;
	FinanceDataMatrix financeDataMatrix;

	public Object getInfo(final String dataform, Map<String, String> map) throws Exception
	{
		switch (dataform) {
			case "kospi" :/* 코스피 */
				this.financeDataMatrix.setMarketURLMap();
				this.financeDataMatrix.setPageDOC(dataform);
				return getUrlInfo.getUrlInfoObject(financeDataMatrix, dataform, dataform);
			case "kosdaq" :/* 코스닥 */
				this.financeDataMatrix.setMarketURLMap();
				this.financeDataMatrix.setPageDOC(dataform);
				return getUrlInfo.getUrlInfoObject(financeDataMatrix, dataform, dataform);
			case "total" : /* 전부 */
				this.financeDataMatrix.setMarketURLMap();
				this.financeDataMatrix.setPageDOC();
				ArrayList<Map<String, String>> totalArr = new ArrayList<Map<String, String>>();
				totalArr.addAll((Collection<? extends Map<String, String>>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "kospi"));
				totalArr.addAll((Collection<? extends Map<String, String>>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "kosdaq"));
				return totalArr;
			default :
				String url = map.get("url");
				String pharseType = map.get("pharseType");
				this.financeDataMatrix.setMarketURLMap(map);
				this.financeDataMatrix.setPageDOC(url);
				return getUrlInfo.getUrlInfoObject(this.financeDataMatrix, url, pharseType);
		}
	}
}
