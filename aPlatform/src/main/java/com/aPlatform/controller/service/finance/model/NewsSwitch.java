package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class NewsSwitch implements UrlFactory
{

	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix)
	{
		Elements contents = null;
		Document doc = financeDataMatrix.getConnectedDoc();
		contents = doc.select(financeDataMatrix.getMarketURLMap().get(financeDataMatrix.getPharseType())).select("a");
		List<List<String>> newsListList = new ArrayList<List<String>>();
		for (int i = 0; i < contents.size(); i++)
		{
			List<String> newsList = new ArrayList<String>();
			newsList.add(contents.get(i).text());
			newsList.add(contents.get(i).attr("href"));
			newsListList.add(newsList);
		}
		return newsListList;
	}
}
