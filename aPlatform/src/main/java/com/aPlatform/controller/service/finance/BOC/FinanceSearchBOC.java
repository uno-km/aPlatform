package com.aPlatform.controller.service.finance.BOC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.VO.FinanceInDTO;
import com.aPlatform.controller.service.finance.VO.FinanceVO;
import com.aPlatform.controller.service.finance.model.GetURLInfo;
import com.aPlatform.mappers.FinanceDataMapper;
import com.aPlatform.utils.constants.FinanceConstants;
@Service
@SuppressWarnings("unchecked")
public class FinanceSearchBOC
{
	@Autowired
	GetURLInfo getUrlInfo;
	@Autowired
	private FinanceDataMapper financeDataMapper;

	Object outData = null;
	String url = "";
	String pharseType = "";

	public Object getInfo(FinanceDataMatrix financeDataMatrix, final String dataform, FinanceInDTO inDTO) throws Exception
	{
		switch (dataform) {
			case "total" : /* 코스피 코스닥 전부 */
				this.setMarketURLMap(financeDataMatrix);
				financeDataMatrix.setPageDOC();
				ArrayList<Map<String, String>> totalArr = new ArrayList<Map<String, String>>();
				totalArr.addAll((Collection<? extends Map<String, String>>) getUrlInfo.getUrlInfoObject(financeDataMatrix, FinanceConstants.KOSPI,
						FinanceConstants.ALLMARKET));
				totalArr.addAll((Collection<? extends Map<String, String>>) getUrlInfo.getUrlInfoObject(financeDataMatrix, FinanceConstants.KOSDAQ,
						FinanceConstants.ALLMARKET));
				return totalArr;
			// case "all" : /* 지수제외 모든 데이터 */
			// return getUrlInfo.getUrlInfoAllObject(financeDataMatrix, inDTO);
			default :
				this.url = inDTO.getUrl();
				this.pharseType = inDTO.getPharseType();
				this.setMarketURLMap(financeDataMatrix, inDTO);
				financeDataMatrix.setPageDOC(this.url);
				this.outData = getUrlInfo.getUrlInfoObject(financeDataMatrix, this.url, this.pharseType);
				return this.outData;
		}
	}
	private void setMarketURLMap(FinanceDataMatrix financeDataMatrix)
	{
		if(financeDataMatrix.isEmptyMarketURLMap())
		{
			List<FinanceVO> mappingUrl = financeDataMapper.getMappingUrl();
			for (FinanceVO innerUrl : mappingUrl)
				financeDataMatrix.putMarketURLMap(innerUrl.getFinType(), innerUrl.getFinUrl());
			List<FinanceVO> mappingPharse = financeDataMapper.getMappingPharse();
			for (FinanceVO innerUrl : mappingPharse)
				financeDataMatrix.putMarketURLMap(innerUrl.getFinType(), innerUrl.getFinPharse());
		}
	}
	private void setMarketURLMap(FinanceDataMatrix financeDataMatrix, FinanceInDTO inDTO)
	{
		setMarketURLMap(financeDataMatrix);
		if(inDTO.getCode() != null)
		{
			financeDataMatrix.putMarketURLMap(FinanceConstants.CODE, inDTO.getCode());
			financeDataMatrix.putMarketURLMap(inDTO.getUrl(), FinanceConstants.DETAIL_URL + inDTO.getCode());
			financeDataMatrix.putMarketURLMap(inDTO.getPharseType(), FinanceConstants.DETAIL_PHARSETYPE);
		}
	}
}
