package com.aPlatform.controller.service.finance.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class FinanceURL
{
	public static Object pharsingURL(FinanceDataMatrix financeDataMatrix, String market, String pharseType) throws IOException
	{
		Document doc = financeDataMatrix.getPageDOCMap().get(market);
		Elements contents = doc.select(financeDataMatrix.getMarketURLMap().get(pharseType));
		HashMap<String, String> outMap = new HashMap<String, String>();
		String[] parsingContainer;

		switch (pharseType) {
			case "index" :
				String[] indexSub = {"_index" , "_per" , "_change" };
				parsingContainer = contents.text().split(" ");
				for (int i = 0; i < indexSub.length; i++)
				{
					outMap.put(market + indexSub[i], parsingContainer[i]);
				}
				return outMap;
			case "buyer" :
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
			case "image" :
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
			case "rankMC" :
				Map<String, ArrayList<String>> outListMap = new LinkedHashMap<>();
				parsingContainer = contents.text().split(" ");
				int rankCnt = 0;
				for (int i = 0; i < parsingContainer.length; i++)
				{
					ArrayList<String> innerArr = new ArrayList<>();
					for (int innerCnt = 0; innerCnt < 5; innerCnt++)
					{
						if(parsingContainer[i + innerCnt].equals("보합"))
						{
							innerArr.add("0");
							innerArr.add(parsingContainer[i + innerCnt]);
							innerArr.add(parsingContainer[i + innerCnt + 1]);
							i--;
							break;
						}
						innerArr.add(parsingContainer[i + innerCnt]);
					}
					outListMap.put(Integer.toString(rankCnt), innerArr);
					rankCnt++;
					i = i + 4;
				}
				return outListMap;
			case "detail" :
				List<String> infoTitleList = contents.eachText();
				infoTitleList = infoTitleList.subList(3, 19);
				Elements tdElements = doc.select("td");
				Elements thElements = doc.select("th");
				List<List<String>> dtlOutList = new ArrayList<List<String>>();
				dtlOutList.add(infoTitleList);
				int thCnt = 0;
				List<String> monthList = new ArrayList<String>();
				for (int i = 0; i < thElements.size(); i++)
				{
					if(thCnt == 10)
					{
						break;
					}
					if(thElements.get(i).text().contains("."))
					{
						monthList.add(thElements.get(i).text());
						thCnt++;
					}
				}
				dtlOutList.add(monthList);
				for (int i = 57; i < 216; i++)
				{
					List<String> innerList = new ArrayList<String>();
					for (int j = 0; j < 10; j++)
					{
						innerList.add(tdElements.get(i + j).text());
					}
					dtlOutList.add(innerList);
					i += 9;
				}
				return dtlOutList;
			case "news" :
				Elements qwe = doc.select("a");
				List<List<String>> newsListList = new ArrayList<List<String>>();
				for (int i = 0; i < qwe.size(); i++)
				{
					String o = "<a href=\"/news/news_read.naver?mode=mainnews&";
					if(qwe.get(i).toString().contains(o))
					{
						List<String> newsList = new ArrayList<String>();
						parsingContainer = qwe.get(i).toString().split("<a href=\"");
						newsList.add(qwe.get(i).text());
						newsList.add(parsingContainer[1].split("\" onclick")[0].replace("&amp;", "&"));
						newsListList.add(newsList);
					}
				}
				return newsListList;
		}
		return outMap;
	}
}
