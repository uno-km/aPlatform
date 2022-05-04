package com.aPlatform.controller.service.finance.model;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
@Service
public class GetURLInfo
{
	public Object getUrlInfoObject(final FinanceDataMatrix financeDataMatrix, final String url, final String pharseType) throws Exception
	{
		return FinanceURL.pharsingURL(financeDataMatrix, url, pharseType);
	}
	public Object getUrlInfoAllObject(final FinanceDataMatrix financeDataMatrix, final String url, final String pharseType) throws Exception
	{
		LinkedHashMap<String, Object> outMap = new LinkedHashMap<String, Object>();
		for (int i = 0; i < UrlMileStone.values().length; i++)
			outMap.put("Str", FinanceURL.pharsingURL(financeDataMatrix, url, pharseType));
		return outMap;
	}
}