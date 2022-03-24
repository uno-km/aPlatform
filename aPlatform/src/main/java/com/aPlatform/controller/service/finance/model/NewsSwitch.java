package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class NewsSwitch implements UrlFactory
{

	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix, Document doc, Elements contents, HashMap<String, String> outMap,
			String[] parsingContainer, String market, String pharseType)
	{
		Elements aTag = doc.select("a");
		List<List<String>> newsListList = new ArrayList<List<String>>();
		for (int i = 0; i < aTag.size(); i++)
		{
			String newsStartUrl = "<a href=\"/news/news_read.naver?mode=mainnews&";
			if(aTag.get(i).toString().contains(newsStartUrl))
			{
				List<String> newsList = new ArrayList<String>();
				parsingContainer = aTag.get(i).toString().split("<a href=\"");
				newsList.add(aTag.get(i).text());
				newsList.add(parsingContainer[1].split("\" onclick")[0].replace("&amp;", "&"));
				newsListList.add(newsList);
			}
		}
		return newsListList;
	}

}
