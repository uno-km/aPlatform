package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class EtcIndexSwitch implements UrlFactory
{
	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix)
	{
		HashMap<String, ArrayList<String>> outListMap = new HashMap<>();
		ArrayList<String> exChangeList = new ArrayList<String>();
		ArrayList<String> interestList = new ArrayList<String>();
		ArrayList<String> oilList = new ArrayList<String>();
		ArrayList<String> elementsList = new ArrayList<String>();
		Elements contents = null;
		Document doc = financeDataMatrix.getConnectedDoc();
		contents = doc.select(financeDataMatrix.getMarketURLMap().get(financeDataMatrix.getPharseType()));
		String[] Exc = contents.select("table").get(0).select("tbody").text().split(" ");
		for (String input : Exc)
			if(!input.contains("(")) exChangeList.add(input);
		outListMap.put("exChange", exChangeList);
		String[] Interest = contents.select("table").get(2).select("tbody").text().split(" ");
		for (String input : Interest)
			interestList.add(input);
		outListMap.put("interest", interestList);
		String[] Oil = contents.select("table").get(3).select("tbody").text().split(" ");
		for (String input : Oil)
			oilList.add(input);
		outListMap.put("oil", oilList);
		String[] Elements = contents.select("table").get(5).select("tbody").text().split(" ");
		for (String input : Elements)
			elementsList.add(input);
		outListMap.put("elements", elementsList);
		return outListMap;
	}
}