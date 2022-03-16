package com.aPlatform.controller.service.finance.model;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class BuyerSwitch implements UrlFactory
{
	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix, Document doc, Elements contents, HashMap<String, String> outMap,
			String[] parsingContainer, String market, String pharseType)
	{
		int bcnt = 0;
		String[] buyerSub = {"_ant" , "_org" , "_frg" };
		parsingContainer = contents.text().split(" ");
		for (int i = 1; i < parsingContainer.length; i++)
		{
			if(bcnt == 3)
			{
				break;
			}
			outMap.put(market + buyerSub[bcnt], parsingContainer[i]);
			i++;
			bcnt++;
		}
		return outMap;
	}

}
