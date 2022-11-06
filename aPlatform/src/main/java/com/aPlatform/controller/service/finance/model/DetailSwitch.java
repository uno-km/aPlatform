package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class DetailSwitch implements UrlFactory
{

	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix, Document doc, Elements contents, HashMap<String, String> outMap,
			String[] parsingContainer, String market, String pharseType)
	{
		Map<String, Object> outMapList = new HashMap<>();
		List<List<String>> dtlOutList = new ArrayList<List<String>>();
		contents = doc.select(financeDataMatrix.getMarketURLMap().get(pharseType));
		List<String> infoTitleList = contents.eachText().subList(3, 19);
		List<String> tableHeaderList = new ArrayList<String>();
		Elements tdElements = doc.select("td");
		Elements table = doc.select(".sub_section").last().select("table");
		Element tableHeader = table.select("thead").select("tr").get(1);
		dtlOutList.add(infoTitleList);
		for (int tableHeaderIdx = 0; tableHeaderIdx < tableHeader.childrenSize(); tableHeaderIdx++)
			tableHeaderList.add(tableHeader.child(tableHeaderIdx).text());
		dtlOutList.add(tableHeaderList);
		for (int i = 57; i < 216; i++)
		{
			List<String> innerList = new ArrayList<String>();
			for (int j = 0; j < 10; j++)
				innerList.add(tdElements.get(i + j).text());
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
		infoTodayList.add(no_today.select(".blind").text());
		infoTodayList.add(no_exday.select(".ico").get(0).text());
		infoTodayList.add(no_exday.select(".blind").get(0).text());
		if(no_exday.select(".ico").get(0).text().equals("보합"))
			infoTodayList.add(no_exday.select(".blind").get(1).text());
		else
			infoTodayList.add(no_exday.select(".ico").get(1).text() + no_exday.select(".blind").get(1).text());
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
			String candleChart = "https://ssl.pstatic.net/imgfinance/chart/item/candle/" + candleArr[i] + "/"
					+ financeDataMatrix.getMarketURLMap().get("code") + ".png?sidcode=1647343521521";
			chartCandleList.add(candleChart);
		}
		outMapList.put("areaChart", chartAreaList);
		outMapList.put("candleChart", chartCandleList);
		Elements sichong = doc.select(".first");
		if(sichong.eachText().get(3).split(" ").length == 14)
		{
			sichongList
					.add(sichong.eachText().get(3).split(" ")[2] + sichong.eachText().get(3).split(" ")[3] + sichong.eachText().get(3).split(" ")[4]);
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
			sichongList.add(sichong.eachText().get(3).split(" ")[7]);
			sichongList.add(sichong.eachText().get(3).split(" ")[8]);
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
		perEpsList.add(perEps.eachText().get(0).split("합니다.")[1].split("l")[0]);
		perEpsList.add(perEps.eachText().get(0).split("합니다.")[1].split("l")[1].split(" ")[1]);
		perEpsList.add(perEps.eachText().get(0).split("합니다.")[3].split("l")[0]);
		perEpsList.add(perEps.eachText().get(0).split("합니다.")[3].split("l")[1].split(" ")[1]);
		perEpsList.add(perEps.eachText().get(0).split("합니다.")[4].split("l")[0]);
		perEpsList.add(perEps.eachText().get(0).split("합니다.")[4].split("l")[1].split(" ")[1]);
		outMapList.put("perEpsList", perEpsList);
		Elements same = doc.select(".gray");
		sameList.add(same.eachText().get(1).split(" ")[4]);
		sameList.add(same.eachText().get(1).split(" ")[7]);
		outMapList.put("sameList", sameList);
		return outMapList;
	}
}
