package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class KosdaqSwitch implements UrlFactory
{

	@SuppressWarnings("unchecked")
	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix, Document doc, Elements contents, HashMap<String, String> outMap,
			String[] parsingContainer, String market, String pharseType)
	{
		contents = doc.select(financeDataMatrix.getMarketURLMap().get("index"));
		ArrayList<Map<String, String>> kosdaqArr = new ArrayList<Map<String, String>>();
		/* 하루 움직임 동향 시작*/
		String[] indexSub = {"_index" , "_per" , "_change" };
		parsingContainer = contents.text().split(" ");
		for (int i = 0; i < indexSub.length; i++)
			outMap.put(market + indexSub[i], parsingContainer[i]);
		kosdaqArr.add((Map<String, String>) outMap.clone());
		outMap.clear();
		/* 하루 움직임 동향  종료*/
		/* 매매주체 시작*/
		int bcnt = 0;
		contents = doc.select(financeDataMatrix.getMarketURLMap().get("buyer")).select("span");
		String[] buyerSub = {"_ant" , "_org" , "_frg" };
		for (int i = 0; i < contents.size(); i++)
		{
			if(bcnt == 3) break;
			if(i % 2 == 0)
			{
				outMap.put(market + buyerSub[bcnt], contents.get(i).text());
				bcnt++;
			}
		}
		kosdaqArr.add((Map<String, String>) outMap.clone());
		outMap.clear();
		/* 매매주체  종료*/
		/* 차트 이미지 시작*/
		contents = doc.select(financeDataMatrix.getMarketURLMap().get("image"));
		String[] isub = {"_day" , "_day90" , "_day365" , "_day1095" };
		parsingContainer = contents.toString().split("<img src=\"");
		for (int i = 0; i < contents.size(); i++)
			outMap.put(market + isub[i], contents.get(i).attr("src"));
		kosdaqArr.add((Map<String, String>) outMap.clone());
		/* 차트 이미지 종료*/
		return kosdaqArr;
	}

}
