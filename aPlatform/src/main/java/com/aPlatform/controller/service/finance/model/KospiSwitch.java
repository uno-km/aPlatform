package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class KospiSwitch implements UrlFactory
{

	@SuppressWarnings("unchecked")
	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix, Document doc, Elements contents, HashMap<String, String> outMap,
			String[] parsingContainer, String market, String pharseType)
	{
		ArrayList<Map<String, String>> kospiArr = new ArrayList<Map<String, String>>();
		/* 하루 움직임 동향 */
		contents = doc.select(financeDataMatrix.getMarketURLMap().get("index"));
		String[] indexSub = {"_index" , "_per" , "_change" };
		parsingContainer = contents.text().split(" ");
		for (int i = 0; i < indexSub.length; i++)
			outMap.put(market + indexSub[i], parsingContainer[i]);
		kospiArr.add((Map<String, String>) outMap.clone());
		outMap.clear();
		/* 매매주체 */
		int bcnt = 0;
		contents = doc.select(financeDataMatrix.getMarketURLMap().get("buyer")).select("span");
		String[] buyerSub = {"_ant" , "_org" , "_frg" };
		for (int i = 1; i < contents.size(); i++)
		{
			if(bcnt == 3) break;
			if(i % 2 == 0)
			{
				outMap.put(market + buyerSub[bcnt], contents.get(i).text());
				bcnt++;
			}
		}
		kospiArr.add((Map<String, String>) outMap.clone());
		outMap.clear();
		/* 차트 이미지 */
		contents = doc.select(financeDataMatrix.getMarketURLMap().get("image"));
		String[] isub = {"_day" , "_day90" , "_day365" , "_day1095" };
		for (int i = 0; i < contents.size(); i++)
			outMap.put(market + isub[i], contents.get(i).attr("src"));
		kospiArr.add((Map<String, String>) outMap.clone());
		return kospiArr;
	}

}
