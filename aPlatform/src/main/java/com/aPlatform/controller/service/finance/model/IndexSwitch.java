package com.aPlatform.controller.service.finance.model;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class IndexSwitch implements switchInterface
{
	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix, Document doc, Elements contents, HashMap<String, String> outMap,
			String[] parsingContainer, String market, String pharseType)
	{
		String[] indexSub = {"_index" , "_per" , "_change" };
		parsingContainer = contents.text().split(" ");
		for (int i = 0; i < indexSub.length; i++)
		{
			outMap.put(market + indexSub[i], parsingContainer[i]);
		}
		return outMap;
	}
}
