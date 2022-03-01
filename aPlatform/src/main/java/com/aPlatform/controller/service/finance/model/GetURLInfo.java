package com.aPlatform.controller.service.finance.model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
@SuppressWarnings("unchecked")
public class GetURLInfo
{
	public Map<String, String> getMarketIndex(FinanceDataMatrix financeDataMatrix, String url, String pharseType) throws IOException
	{
		return (Map<String, String>) FinanceURL.pharsingURL(financeDataMatrix, url, pharseType);
	}

	public Map<String, ArrayList<String>> getMapStringArrayList(FinanceDataMatrix financeDataMatrix, String url) throws IOException
	{
		return (Map<String, ArrayList<String>>) FinanceURL.pharsingURL(financeDataMatrix, url, null);
	}
}