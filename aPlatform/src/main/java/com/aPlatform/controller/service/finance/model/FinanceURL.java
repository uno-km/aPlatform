package com.aPlatform.controller.service.finance.model;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class FinanceURL
{
	public static Object pharsingURL(FinanceDataMatrix financeDataMatrix, String market, String pharseType) throws Exception
	{
		Document doc = financeDataMatrix.getPageDOCMap().get(market);
		Elements contents = doc.select(financeDataMatrix.getMarketURLMap().get(pharseType));
		HashMap<String, String> outMap = new HashMap<String, String>();
		String[] parsingContainer = {};

		for (UrlMileStone str : UrlMileStone.values())
		{
			if(pharseType.equalsIgnoreCase(str.toString()))
			{
				Class<?> testClass = Class.forName(str.getValue());
				Object newObj = testClass.newInstance();
				Method method = testClass.getDeclaredMethod("excute", FinanceDataMatrix.class, Document.class, Elements.class, HashMap.class,
						String[].class, String.class, String.class);
				return method.invoke(newObj, financeDataMatrix, doc, contents, outMap, parsingContainer, market, pharseType);
			}
		}
		return outMap;
	}
}
