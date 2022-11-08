package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.utils.FinanceUtils;

public class DetailSwitch implements UrlFactory
{

	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix, Document document, Elements contents, HashMap<String, String> outMap,
			String[] parsingContainer, String market, String pharseType)
	{
		Map<String, Object> outMapList = new HashMap<>();
		List<List<String>> tableBodyList = new ArrayList<List<String>>();
		List<String> tableHeaderList = new ArrayList<String>();

		Elements no_today = document.select(".no_today");
		Elements no_exday = document.select(".no_exday");
		Elements table = document.select(".sub_section").last().select("table");
		Elements tableBody = table.select("tbody").select("tr");
		Element tableHeader = table.select("thead").select("tr").get(1);
		Elements sichong = document.select(".first").select("table").select("tbody").select("tr");

		Elements forign = document.select(".lwidth");
		Elements opinon = document.select(".rwidth");
		Elements perEps = document.select(".per_table");
		Elements same = document.select(".gray");
		for (int tableHeaderIdx = 0; tableHeaderIdx < tableHeader.childrenSize(); tableHeaderIdx++)
		{
			tableHeaderList.add(tableHeader.child(tableHeaderIdx).text());
		}
		tableBodyList.add(tableHeaderList);

		for (int tableBodyIdx = 0; tableBodyIdx < tableBody.size(); tableBodyIdx++)
		{
			List<String> innerList = new ArrayList<String>();
			innerList.add(tableBody.get(tableBodyIdx).select("th").text());
			for (int tagIdx = 0; tagIdx < tableBody.get(tableBodyIdx).select("td").size(); tagIdx++)
			{
				innerList.add(tableBody.get(tableBodyIdx).select("td").get(tagIdx).text());
			}
			tableBodyList.add(innerList);
		}
		outMapList.put("statement", tableBodyList);

		List<String> infoTodayList = new ArrayList<>();
		List<String> chartAreaList = new ArrayList<>();
		List<String> chartCandleList = new ArrayList<>();
		List<String> sichongList = new ArrayList<>();
		List<String> forignList = new ArrayList<>();
		List<String> opinionList = new ArrayList<>();
		List<String> sameList = new ArrayList<>();
		List<String> perEpsList = new ArrayList<>();
		infoTodayList.add(no_today.select(".blind").text());
		infoTodayList.add(no_exday.select(".ico").get(0).text());
		infoTodayList.add(no_exday.select(".blind").get(0).text());
		if(no_exday.select(".ico").get(0).text().equals("보합"))
			infoTodayList.add(no_exday.select(".blind").get(1).text());
		else
			infoTodayList.add(no_exday.select(".ico").get(1).text() + no_exday.select(".blind").get(1).text());
		outMapList.put("today", infoTodayList);
		for (int i = 0; i < FinanceUtils.CHART_AREA_ARR.length; i++)
		{
			String araeChart = "https://ssl.pstatic.net/imgfinance/chart/item/area/" + FinanceUtils.CHART_AREA_ARR[i] + "/"
					+ financeDataMatrix.getMarketURLMap().get("code") + ".png?sidcode=1647343521521";
			chartAreaList.add(araeChart);
		}
		for (int i = 0; i < FinanceUtils.CHART_CANDLE_ARR.length; i++)
		{
			String candleChart = "https://ssl.pstatic.net/imgfinance/chart/item/candle/" + FinanceUtils.CHART_CANDLE_ARR[i] + "/"
					+ financeDataMatrix.getMarketURLMap().get("code") + ".png?sidcode=1647343521521";
			chartCandleList.add(candleChart);
		}
		outMapList.put("areaChart", chartAreaList);
		outMapList.put("candleChart", chartCandleList);
		sichongList.add(sichong.get(0).select("td").text().replace(" ", ""));
		sichongList.add(sichong.get(1).select("td").text().split(" ")[0]);// 코스피
		sichongList.add(sichong.get(1).select("td").text().split(" ")[1]);
		sichongList.add(sichong.get(2).select("td").text());
		sichongList.add(sichong.get(3).select("td").text().split(" ")[0]);
		outMapList.put("sichongList", sichongList);
		forignList.add(forign.eachText().get(0).split(" ")[0]);
		forignList.add(forign.eachText().get(0).split(" ")[2]);
		forignList.add(forign.eachText().get(0).split(" ")[3]);
		forignList.add(forign.eachText().get(0).split("전력")[1].split(" ")[1]);
		outMapList.put("forignList", forignList);
		opinionList.add(opinon.text().split("l")[1].split(" ")[1]);
		opinionList.add(opinon.text().split("l")[2].split(" ")[1]);
		opinionList.add(opinon.text().split("l")[3].split(" ")[1]);
		opinionList.add(opinon.text().split("l")[4].replace(" ", ""));
		outMapList.put("opinionList", opinionList);
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
	}
}
