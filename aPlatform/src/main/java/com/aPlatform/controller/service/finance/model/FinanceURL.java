package com.aPlatform.controller.service.finance.model;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class FinanceURL
{
	public static Object pharsingURL(FinanceDataMatrix financeDataMatrix, String url, String pharseType) throws Exception
	{
		Document doc = financeDataMatrix.getPageDOCMap().get(url);
		Elements contents = null;
		HashMap<String, String> outMap = new HashMap<String, String>();
		String[] parsingContainer = {};

		for (UrlMileStone str : UrlMileStone.values())
		{
			if(pharseType.equalsIgnoreCase(str.toString()))
			{
				Class<?> excuteClass = Class.forName(str.getValue());
				Object newInstanceObj = excuteClass.newInstance();
				Method excuteMethod = excuteClass.getDeclaredMethod("excute", FinanceDataMatrix.class, Document.class, Elements.class, HashMap.class,
						String[].class, String.class, String.class);
				return excuteMethod.invoke(newInstanceObj, financeDataMatrix, doc, contents, outMap, parsingContainer, url, pharseType);
			}
		}
		return outMap;
	}
}
