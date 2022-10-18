package com.aPlatform.controller.service.finance.BOC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.VO.FinanceInDTO;
import com.aPlatform.controller.service.finance.model.GetURLInfo;
import com.aPlatform.utils.FinanceUtils;
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
		financeDataMatrix.clearMatrix();
		switch (dataform) {
			case "total" : /* 코스피 코스닥 전부 */
				financeDataMatrix.setMarketURLMap();
				financeDataMatrix.setPageDOC();
				ArrayList<Map<String, String>> totalArr = new ArrayList<Map<String, String>>();
				totalArr.addAll((Collection<? extends Map<String, String>>) getUrlInfo.getUrlInfoObject(financeDataMatrix, FinanceUtils.KOSPI,
						FinanceUtils.KOSPI));
				totalArr.addAll((Collection<? extends Map<String, String>>) getUrlInfo.getUrlInfoObject(financeDataMatrix, FinanceUtils.KOSDAQ,
						FinanceUtils.KOSDAQ));
				return totalArr;
			// case "all" : /* 지수제외 모든 데이터 */
			// return getUrlInfo.getUrlInfoAllObject(financeDataMatrix, inDTO);
			default :
				this.url = inDTO.getUrl();
				this.pharseType = inDTO.getPharseType();
				financeDataMatrix.setMarketURLMap(inDTO);
				financeDataMatrix.setPageDOC(this.url);
				this.outData = getUrlInfo.getUrlInfoObject(financeDataMatrix, this.url, this.pharseType);
				return this.outData;
		}
	}
}
