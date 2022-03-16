package com.aPlatform.controller.service.finance.model;

import java.io.IOException;
import java.util.HashMap;

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
		String[] parsingContainer = {};
		switch (pharseType) {
			case "index" :
				IndexSwitch indexSwitch = new IndexSwitch();
				return indexSwitch.excute(financeDataMatrix, doc, contents, outMap, parsingContainer, market, pharseType);
			case "buyer" :
				BuyerSwitch buyerSwitch = new BuyerSwitch();
				return buyerSwitch.excute(financeDataMatrix, doc, contents, outMap, parsingContainer, market, pharseType);
			case "image" :
				ImageSwitch imageSwitch = new ImageSwitch();
				return imageSwitch.excute(financeDataMatrix, doc, contents, outMap, parsingContainer, market, pharseType);
			case "rankMC" :
				RankSwitch rankSwitch = new RankSwitch();
				return rankSwitch.excute(financeDataMatrix, doc, contents, outMap, parsingContainer, market, pharseType);
			case "detail" :
				DetailSwitch detailSwitch = new DetailSwitch();
				return detailSwitch.excute(financeDataMatrix, doc, contents, outMap, parsingContainer, market, pharseType);
			case "news" :
				NewsSwitch newsSwitch = new NewsSwitch();
				return newsSwitch.excute(financeDataMatrix, doc, contents, outMap, parsingContainer, market, pharseType);
		}
		return outMap;
	}
}
