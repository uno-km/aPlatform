package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.List;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.arch.SuperFinance;
import com.aPlatform.utils.Constants;

public class NewsSwitch extends SuperFinance implements UrlFactory
{

	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix)
	{
		super.doc = financeDataMatrix.getConnectedDoc();
		super.contents = super.doc.select(financeDataMatrix.getMarketURLMap().get(financeDataMatrix.getPharseType())).select(Constants.A);
		List<List<String>> newsListList = new ArrayList<List<String>>();
		for (int i = 0; i < super.contents.size(); i++)
		{
			List<String> newsList = new ArrayList<String>();
			newsList.add(super.contents.get(i).text());
			newsList.add(super.contents.get(i).attr(Constants.HREF));
			newsListList.add(newsList);
		}
		return newsListList;
	}
}
