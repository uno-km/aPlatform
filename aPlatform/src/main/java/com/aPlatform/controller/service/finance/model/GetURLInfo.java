package com.aPlatform.controller.service.finance.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
@Service
public class GetURLInfo
{
	public Object getUrlInfoObject(final FinanceDataMatrix financeDataMatrix, final String url, final String pharseType) throws Exception
	{
		return FinanceURL.pharsingURL(financeDataMatrix, url, pharseType);
	}
	public Object getUrlInfoAllObject(final FinanceDataMatrix financeDataMatrix, final Map<String, Object> map) throws Exception
	{
		// 여기에 맵을 다루는 로직을 만든다
		UrlMileStone[] urlArr = UrlMileStone.values();
		ArrayList<String> outMapKeys = new ArrayList<String>();
		LinkedHashMap<String, Object> outMap = new LinkedHashMap<String, Object>();
		for (UrlMileStone urls : urlArr)
		{
			outMapKeys.add(urls.name());
			// outMap.put(urls.name(), FinanceURL.pharsingURL(financeDataMatrix, urls.name(), pharseType));
		}
		for (int i = 0; i < urlArr.length; i++)
		{
			// outMap.put("Str", FinanceURL.pharsingURL(financeDataMatrix, url, pharseType));
		}
		return outMap;
	}
}