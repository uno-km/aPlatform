package com.unoCode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.aPlatform.controller.service.finance.BOC.FinanceDataMatrix;
@SuppressWarnings("unchecked")
public class GetURLInfo
{
	public Map<String, String> getMarketIndex(FinanceDataMatrix financeDataMatrix,String url, String pharseType) throws IOException
	{
		return (Map<String, String>) Utils.pharsingURL(financeDataMatrix, url, pharseType);
	}

	public Map<String, ArrayList<String>> getMarketRanking(FinanceDataMatrix financeDataMatrix, String url) throws IOException
	{
		return (Map<String, ArrayList<String>>) Utils.pharsingURL(financeDataMatrix, url, null);
	}
}