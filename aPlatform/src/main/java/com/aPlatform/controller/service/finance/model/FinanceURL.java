package com.aPlatform.controller.service.finance.model;

import java.lang.reflect.Method;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class FinanceURL
{
	public static Object pharsingURL(FinanceDataMatrix financeDataMatrix, String url, String pharseType) throws Exception
	{
		financeDataMatrix.setMarket(url);
		financeDataMatrix.setPharseType(pharseType);
		financeDataMatrix.setConnectedDoc(financeDataMatrix.getPageDOCMap().get(url));
		for (UrlMileStone str : UrlMileStone.values())
		{
			if(pharseType.equalsIgnoreCase(str.toString()))
			{
				Class<?> excuteClass = Class.forName(str.getValue());
				Object newInstanceObj = excuteClass.newInstance();
				Method excuteMethod = excuteClass.getDeclaredMethod("excute", FinanceDataMatrix.class);
				return excuteMethod.invoke(newInstanceObj, financeDataMatrix);
			}
		}
		return null;
	}
}
