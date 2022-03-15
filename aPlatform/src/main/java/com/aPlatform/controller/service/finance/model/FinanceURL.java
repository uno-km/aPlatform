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
				Map<String, Object> outMapList = new HashMap<>();
				List<List<String>> dtlOutList = new ArrayList<List<String>>();
				List<String> infoTitleList = contents.eachText().subList(3, 19);
				List<String> monthList = new ArrayList<String>();
				Elements tdElements = doc.select("td");
				Elements thElements = doc.select("th");
				dtlOutList.add(infoTitleList);
				int thCnt = 0;
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
				outMapList.put("statement", dtlOutList);
				List<String> infoTodayList = new ArrayList<>();
				List<String> chartAreaList = new ArrayList<>();
				List<String> chartCandleList = new ArrayList<>();
				List<String> sichongList = new ArrayList<>();
				List<String> forignList = new ArrayList<>();
				List<String> opinionList = new ArrayList<>();
				List<String> sameList = new ArrayList<>();
				List<String> perEpsList = new ArrayList<>();
				Elements no_today = doc.select(".no_today");
				Elements no_exday = doc.select(".no_exday");
				infoTodayList.add(no_today.text().split(" ")[0]);
				infoTodayList.add(no_exday.text().split("l")[0].split(" ")[1]);
				infoTodayList.add(no_exday.text().split("l")[0].split(" ")[2]);
				if(no_exday.text().split("l")[0].split(" ")[1].equals("보합"))
				{
					infoTodayList.add(no_exday.text().split("l")[1].split(" ")[1]);
				}
				else
				{
					infoTodayList.add(no_exday.text().split("l")[1].split(" ")[1] + no_exday.text().split("l")[1].split(" ")[2]);
				}
				outMapList.put("today", infoTodayList);
				String[] areaArr = {"day" , "week" , "month3" , "year" , "year3" , "year5" , "year10" };
				String[] candleArr = {"day" , "week" , "month" };
				for (int i = 0; i < areaArr.length; i++)
				{
					String araeChart = "https://ssl.pstatic.net/imgfinance/chart/item/area/" + areaArr[i] + "/"
							+ financeDataMatrix.getMarketURLMap().get("code") + ".png?sidcode=1647343521521";
					chartAreaList.add(araeChart);
				}
				for (int i = 0; i < candleArr.length; i++)
				{
					String candleChart = "https://ssl.pstatic.net/imgfinance/chart/item/area/" + candleArr[i] + "/"
							+ financeDataMatrix.getMarketURLMap().get("code") + ".png?sidcode=1647343521521";
					chartCandleList.add(candleChart);
				}
				outMapList.put("areaChart", chartAreaList);
				outMapList.put("candleChart", chartCandleList);
				Elements sichong = doc.select(".first");
				if(sichong.eachText().get(3).split(" ").length == 14)
				{
					sichongList.add(sichong.eachText().get(3).split(" ")[2] + sichong.eachText().get(3).split(" ")[3]
							+ sichong.eachText().get(3).split(" ")[4]);
					sichongList.add(sichong.eachText().get(3).split(" ")[6]);
					sichongList.add(sichong.eachText().get(3).split(" ")[7]);
					sichongList.add(sichong.eachText().get(3).split(" ")[9]);
					sichongList.add(sichong.eachText().get(3).split(" ")[11]);
				}
				else
				{
					sichongList.add(sichong.eachText().get(3).split(" ")[2] + sichong.eachText().get(3).split(" ")[3]);
					sichongList.add(sichong.eachText().get(3).split(" ")[5]);
					sichongList.add(sichong.eachText().get(3).split(" ")[6]);
					sichongList.add(sichong.eachText().get(3).split(" ")[8]);
					sichongList.add(sichong.eachText().get(3).split(" ")[10]);
					sichongList.add(sichong.eachText().get(3).split(" ")[12]);
				}
				outMapList.put("sichongList", sichongList);
				Elements forign = doc.select(".lwidth");
				forignList.add(forign.eachText().get(0).split(" ")[0]);
				forignList.add(forign.eachText().get(0).split(" ")[2]);
				forignList.add(forign.eachText().get(0).split(" ")[3]);
				forignList.add(forign.eachText().get(0).split("전력")[1].split(" ")[1]);
				outMapList.put("forignList", forignList);
				Elements opinon = doc.select(".rwidth");
				opinionList.add(opinon.text().split("l")[1].split(" ")[1]);
				opinionList.add(opinon.text().split("l")[2].split(" ")[1]);
				opinionList.add(opinon.text().split("l")[3].split(" ")[1]);
				opinionList.add(opinon.text().split("l")[4].replace(" ", ""));
				outMapList.put("opinionList", opinionList);
				Elements perEps = doc.select(".per_table");
				Elements same = doc.select(".gray");
				perEpsList.add(perEps.eachText().get(0).split("합니다.")[1].split("l")[0]);
				perEpsList.add(perEps.eachText().get(0).split("합니다.")[1].split("l")[1].split(" ")[1]);
				perEpsList.add(perEps.eachText().get(0).split("합니다.")[3].split("l")[0]);
				perEpsList.add(perEps.eachText().get(0).split("합니다.")[3].split("l")[1].split(" ")[1]);
				perEpsList.add(perEps.eachText().get(0).split("합니다.")[4].split("l")[0]);
				perEpsList.add(perEps.eachText().get(0).split("합니다.")[4].split("l")[1].split(" ")[1]);
				outMapList.put("perEpsList", perEpsList);
				sameList.add(same.eachText().get(1).split(" ")[4]);
				sameList.add(same.eachText().get(1).split(" ")[7]);
				outMapList.put("sameList", sameList);
				return outMapList;
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
