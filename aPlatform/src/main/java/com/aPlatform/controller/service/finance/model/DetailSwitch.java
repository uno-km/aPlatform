package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.utils.Constants;
import com.aPlatform.utils.constants.FinanceConstants;
import com.aPlatform.utils.constants.HtmlConstants;

public class DetailSwitch implements FinanceDataType
{
	
	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix)
	{
		Map<String, Object> outMapList = new HashMap<>();
		Element document = financeDataMatrix.getConnectedDoc().select("#middle").get(0);
		this.setTableDefaultData(outMapList, document);
		this.setInfoTodayList(outMapList, document);
		this.setChartDataList(outMapList, financeDataMatrix);
		this.setSichongList(outMapList, document);
		this.setForignList(outMapList, document);
		this.setOpinionsList(outMapList, document);
		this.setPerEpsList(outMapList, document);
		this.setSameList(outMapList, document);
		return outMapList;
	}
	
	private void setTableDefaultData(Map<String, Object> outMapList, Element document)
	{
		List<List<String>> tableBodyList = new ArrayList<List<String>>();
		List<String> tableHeaderList = new ArrayList<String>();
		Elements table = document.select(".sub_section").last().select(HtmlConstants.TABLE);
		Elements tableBody = table.select(HtmlConstants.TBODY).select(HtmlConstants.TR);
		Element tableHeaders = table.select(HtmlConstants.THEAD).select(HtmlConstants.TR).get(1);
		for (Element tableHeader : tableHeaders.children()) tableHeaderList.add(tableHeader.text());
		tableBodyList.add(tableHeaderList);
		for (Element indexedTable : tableBody)
		{
			List<String> innerList = new ArrayList<String>();
			innerList.add(indexedTable.select(HtmlConstants.TH).text());
			for (Element TD : indexedTable.select(HtmlConstants.TD)) innerList.add(TD.text());
			tableBodyList.add(innerList);
		}
		outMapList.put("statement", tableBodyList);
	}
	private void setInfoTodayList(Map<String, Object> outMapList, Element document)
	{
		Elements no_today = document.select(".no_today");
		Elements no_exday = document.select(".no_exday");
		List<String> infoTodayList = new ArrayList<>();
		infoTodayList.add(no_today.select(".blind").text());
		infoTodayList.add(no_exday.select(".ico").get(0).text());
		infoTodayList.add(no_exday.select(".blind").get(0).text());
		if (no_exday.select(".ico").get(0).text().equals(FinanceConstants.STEADY)) infoTodayList.add(no_exday.select(".blind").get(1).text());
		else infoTodayList.add(no_exday.select(".ico").get(1).text() + no_exday.select(".blind").get(1).text());
		outMapList.put("today", infoTodayList);
		
	}
	private void setChartDataList(Map<String, Object> outMapList, FinanceDataMatrix financeDataMatrix)
	{
		List<String> chartAreaList = new ArrayList<>();
		List<String> chartCandleList = new ArrayList<>();
		for (String chartArea : FinanceConstants.CHART_AREA_ARR)
			chartAreaList.add(FinanceConstants.CHART_AREA_IMG_URL + chartArea + "/" + financeDataMatrix.getMarketURLMap().get("code") + FinanceConstants.CHART_COMMON_IMG_URL);
		for (String chartArea : FinanceConstants.CHART_CANDLE_ARR)
			chartCandleList.add(FinanceConstants.CHART_CANDLE_IMG_URL + chartArea + "/" + financeDataMatrix.getMarketURLMap().get("code") + FinanceConstants.CHART_COMMON_IMG_URL);
		outMapList.put("areaChart", chartAreaList);
		outMapList.put("candleChart", chartCandleList);
	}
	private void setSameList(Map<String, Object> outMapList, Element document)
	{
		List<String> sameList = new ArrayList<>();
		Elements same = document.select(".gray").get(1).select(HtmlConstants.TABLE).select(HtmlConstants.TBODY).select(HtmlConstants.TR).select(HtmlConstants.TD);;
		sameList.add(same.get(0).text());
		sameList.add(same.get(1).text());
		outMapList.put("sameList", sameList);
	}
	private void setPerEpsList(Map<String, Object> outMapList, Element document)
	{
		List<String> perEpsList = new ArrayList<>();
		Elements perEps = document.select(".per_table").select(HtmlConstants.TBODY);
		perEpsList.add(perEps.get(0).select(HtmlConstants.TD).select(HtmlConstants.EM).get(0).text() + FinanceConstants.MULTI_STR);
		perEpsList.add(perEps.get(0).select(HtmlConstants.TD).select(HtmlConstants.EM).get(1).text() + FinanceConstants.WON_STR);
		perEpsList.add(perEps.get(1).select(HtmlConstants.TD).select(HtmlConstants.EM).get(0).text() + FinanceConstants.MULTI_STR);
		perEpsList.add(perEps.get(1).select(HtmlConstants.TD).select(HtmlConstants.EM).get(1).text() + FinanceConstants.WON_STR);
		perEpsList.add(perEps.get(1).select(HtmlConstants.TD).select(HtmlConstants.EM).get(3).text() + FinanceConstants.MULTI_STR);
		perEpsList.add(perEps.get(1).select(HtmlConstants.TD).select(HtmlConstants.EM).get(4).text() + FinanceConstants.WON_STR);
		outMapList.put("perEpsList", perEpsList);
	}
	private void setOpinionsList(Map<String, Object> outMapList, Element document)
	{
		List<String> opinionList = new ArrayList<>();
		Elements opinon = document.select(".rwidth").select("table").select("tbody").select("tr");
		opinionList.add(opinon.get(0).select(HtmlConstants.TD).select(HtmlConstants.SPAN).get(0).text());
		opinionList.add(opinon.get(0).select(HtmlConstants.TD).select(HtmlConstants.EM).get(1).text());
		opinionList.add(opinon.get(1).select(HtmlConstants.TD).select(HtmlConstants.EM).get(0).text() + FinanceConstants.WON_STR);
		opinionList.add(opinon.get(1).select(HtmlConstants.TD).select(HtmlConstants.EM).get(1).text() + FinanceConstants.WON_STR);
		outMapList.put("opinionList", opinionList);
		
	}
	private void setForignList(Map<String, Object> outMapList, Element document)
	{
		List<String> forignList = new ArrayList<>();
		Elements forign = document.select(".lwidth").select(HtmlConstants.TABLE).select(HtmlConstants.TBODY).select(HtmlConstants.TR);
		forignList.add(forign.get(0).select(HtmlConstants.TH).text());
		forignList.add(forign.get(0).select(HtmlConstants.TD).text());
		forignList.add(forign.get(1).select(HtmlConstants.TH).text());
		forignList.add(forign.get(1).select(HtmlConstants.TD).text());
		forignList.add(forign.get(2).select(HtmlConstants.TH).select(HtmlConstants.STRONG).text());
		forignList.add(forign.get(2).select(HtmlConstants.TD).text());
		outMapList.put("forignList", forignList);
	}
	private void setSichongList(Map<String, Object> outMapList, Element document)
	{
		List<String> sichongList = new ArrayList<>();
		Elements sichong = document.select(".first").select(HtmlConstants.TABLE).select(HtmlConstants.TBODY).select(HtmlConstants.TR);
		sichongList.add(sichong.get(0).select(HtmlConstants.TD).text().replace(" ", ""));
		sichongList.add(sichong.get(1).select(HtmlConstants.TD).textNodes().get(0).text());// 코스피
		sichongList.add(sichong.get(1).select(HtmlConstants.TD).select(HtmlConstants.EM).text() + FinanceConstants.RANK_STR);
		sichongList.add(sichong.get(2).select(HtmlConstants.TD).text());
		sichongList.add(sichong.get(3).select(HtmlConstants.TD).select(HtmlConstants.EM).get(0).text());
		outMapList.put("sichongList", sichongList);
	}
	
	@Override
	public String getType()
	{
		return Constants.DETAIL_SHEREINFO_URL;
	}
}
