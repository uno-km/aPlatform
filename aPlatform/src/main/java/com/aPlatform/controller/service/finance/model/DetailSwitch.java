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
import com.aPlatform.utils.HtmlUtils;

public class DetailSwitch implements UrlFactory
{

	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix, Document doc, Elements contents, HashMap<String, String> outMap,
			String[] parsingContainer, String market, String pharseType)
	{
		Map<String, Object> outMapList = new HashMap<>();
		List<List<String>> tableBodyList = new ArrayList<List<String>>();
		List<String> tableHeaderList = new ArrayList<String>();
		Element document = doc.select("#middle").get(0);
		Elements no_today = document.select(".no_today");
		Elements no_exday = document.select(".no_exday");
		Elements table = document.select(".sub_section").last().select(HtmlUtils.TABLE);
		Elements tableBody = table.select(HtmlUtils.TBODY).select(HtmlUtils.TR);
		Element tableHeader = table.select(HtmlUtils.THEAD).select(HtmlUtils.TR).get(1);
		Elements sichong = document.select(".first").select(HtmlUtils.TABLE).select(HtmlUtils.TBODY).select(HtmlUtils.TR);
		Elements forign = document.select(".lwidth").select(HtmlUtils.TABLE).select(HtmlUtils.TBODY).select(HtmlUtils.TR);
		Elements opinon = document.select(".rwidth").select("table").select("tbody").select("tr");
		Elements perEps = document.select(".per_table").select(HtmlUtils.TBODY);
		Elements same = document.select(".gray").get(1).select(HtmlUtils.TABLE).select(HtmlUtils.TBODY).select(HtmlUtils.TR).select(HtmlUtils.TD);
		;
		for (int tableHeaderIdx = 0; tableHeaderIdx < tableHeader.childrenSize(); tableHeaderIdx++)
		{
			tableHeaderList.add(tableHeader.child(tableHeaderIdx).text());
		}
		tableBodyList.add(tableHeaderList);

		for (int tableBodyIdx = 0; tableBodyIdx < tableBody.size(); tableBodyIdx++)
		{
			List<String> innerList = new ArrayList<String>();
			innerList.add(tableBody.get(tableBodyIdx).select(HtmlUtils.TH).text());
			for (int tagIdx = 0; tagIdx < tableBody.get(tableBodyIdx).select(HtmlUtils.TD).size(); tagIdx++)
			{
				innerList.add(tableBody.get(tableBodyIdx).select(HtmlUtils.TD).get(tagIdx).text());
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
		if(no_exday.select(".ico").get(0).text().equals(FinanceUtils.STEADY))
			infoTodayList.add(no_exday.select(".blind").get(1).text());
		else
			infoTodayList.add(no_exday.select(".ico").get(1).text() + no_exday.select(".blind").get(1).text());
		for (int i = 0; i < FinanceUtils.CHART_AREA_ARR.length; i++)
		{
			String araeChart = FinanceUtils.CHART_AREA_IMG_URL + FinanceUtils.CHART_AREA_ARR[i] + "/"
					+ financeDataMatrix.getMarketURLMap().get("code") + FinanceUtils.CHART_COMMON_IMG_URL;
			chartAreaList.add(araeChart);
		}
		for (int i = 0; i < FinanceUtils.CHART_CANDLE_ARR.length; i++)
		{
			String candleChart = FinanceUtils.CHART_CANDLE_IMG_URL + FinanceUtils.CHART_CANDLE_ARR[i] + "/"
					+ financeDataMatrix.getMarketURLMap().get("code") + FinanceUtils.CHART_COMMON_IMG_URL;
			chartCandleList.add(candleChart);
		}
		sichongList.add(sichong.get(0).select(HtmlUtils.TD).text().replace(" ", ""));
		sichongList.add(sichong.get(1).select(HtmlUtils.TD).textNodes().get(0).text());// 코스피
		sichongList.add(sichong.get(1).select(HtmlUtils.TD).select(HtmlUtils.EM).text() + FinanceUtils.RANK_STR);
		sichongList.add(sichong.get(2).select(HtmlUtils.TD).text());
		sichongList.add(sichong.get(3).select(HtmlUtils.TD).select(HtmlUtils.EM).get(0).text());
		forignList.add(forign.get(0).select(HtmlUtils.TH).text());
		forignList.add(forign.get(0).select(HtmlUtils.TD).text());
		forignList.add(forign.get(1).select(HtmlUtils.TH).text());
		forignList.add(forign.get(1).select(HtmlUtils.TD).text());
		forignList.add(forign.get(2).select(HtmlUtils.TH).select(HtmlUtils.STRONG).text());
		forignList.add(forign.get(2).select(HtmlUtils.TD).text());
		opinionList.add(opinon.get(0).select(HtmlUtils.TD).select(HtmlUtils.SPAN).get(0).text());
		opinionList.add(opinon.get(0).select(HtmlUtils.TD).select(HtmlUtils.EM).get(1).text());
		opinionList.add(opinon.get(1).select(HtmlUtils.TD).select(HtmlUtils.EM).get(0).text() + FinanceUtils.WON_STR);
		opinionList.add(opinon.get(1).select(HtmlUtils.TD).select(HtmlUtils.EM).get(1).text() + FinanceUtils.WON_STR);
		perEpsList.add(perEps.get(0).select(HtmlUtils.TD).select(HtmlUtils.EM).get(0).text() + FinanceUtils.MULTI_STR);
		perEpsList.add(perEps.get(0).select(HtmlUtils.TD).select(HtmlUtils.EM).get(1).text() + FinanceUtils.WON_STR);
		perEpsList.add(perEps.get(1).select(HtmlUtils.TD).select(HtmlUtils.EM).get(0).text() + FinanceUtils.MULTI_STR);
		perEpsList.add(perEps.get(1).select(HtmlUtils.TD).select(HtmlUtils.EM).get(1).text() + FinanceUtils.WON_STR);
		perEpsList.add(perEps.get(1).select(HtmlUtils.TD).select(HtmlUtils.EM).get(3).text() + FinanceUtils.MULTI_STR);
		perEpsList.add(perEps.get(1).select(HtmlUtils.TD).select(HtmlUtils.EM).get(4).text() + FinanceUtils.WON_STR);
		sameList.add(same.get(0).text());
		sameList.add(same.get(1).text());
		outMapList.put("today", infoTodayList);
		outMapList.put("sichongList", sichongList);
		outMapList.put("areaChart", chartAreaList);
		outMapList.put("candleChart", chartCandleList);
		outMapList.put("forignList", forignList);
		outMapList.put("opinionList", opinionList);
		outMapList.put("perEpsList", perEpsList);
		outMapList.put("sameList", sameList);
		return outMapList;
	}
}
