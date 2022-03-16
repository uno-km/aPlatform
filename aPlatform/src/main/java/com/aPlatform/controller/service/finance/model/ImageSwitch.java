package com.aPlatform.controller.service.finance.model;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class ImageSwitch implements switchInterface
{

	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix, Document doc, Elements contents, HashMap<String, String> outMap,
			String[] parsingContainer, String market, String pharseType)
	{
		int icnt = 0;
		String[] isub = {"_day" , "_day90" , "_day365" , "_day1095" };
		parsingContainer = contents.toString().split("<img src=\"");
		for (int i = 0; i < parsingContainer.length; i++)
		{
			if(!parsingContainer[i].isEmpty())
			{
				outMap.put(market + isub[icnt], parsingContainer[i].split("\" alt=")[0]);
				icnt++;
			}
		}
		return outMap;
	}

}
