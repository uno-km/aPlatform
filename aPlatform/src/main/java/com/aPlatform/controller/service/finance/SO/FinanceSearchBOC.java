package com.aPlatform.controller.service.finance.SO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.VO.FinanceInDTO;
import com.aPlatform.controller.service.finance.model.GetURLInfo;
@Service
@SuppressWarnings("unchecked")
public class FinanceSearchBOC
{
	@Autowired
	GetURLInfo getUrlInfo;

	Object outData = null;
	String url = "";
	String pharseType = "";
	public Object getInfo(FinanceDataMatrix financeDataMatrix, final String dataform, FinanceInDTO inDTO) throws Exception
	{
		this.url = inDTO.getUrl();
		this.pharseType = inDTO.getPharseType();
		switch (dataform) {
			case "total" : /* 코스피 코스닥 전부 */
				financeDataMatrix.setMarketURLMap();
				financeDataMatrix.setPageDOC();
				ArrayList<Map<String, String>> totalArr = new ArrayList<Map<String, String>>();
				totalArr.addAll((Collection<? extends Map<String, String>>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "kospi"));
				totalArr.addAll((Collection<? extends Map<String, String>>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "kosdaq"));
				return totalArr;
			// case "all" : /* 지수제외 모든 데이터 */
			// return getUrlInfo.getUrlInfoAllObject(financeDataMatrix, inDTO);
			default :
				financeDataMatrix.setMarketURLMap(inDTO);
				financeDataMatrix.setPageDOC(this.url);
				this.outData = getUrlInfo.getUrlInfoObject(financeDataMatrix, this.url, this.pharseType);
				financeDataMatrix.clearMatrix();
				return this.outData;
		}
	}
}
