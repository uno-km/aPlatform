package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public class RankSwitch implements switchInterface
{

	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix, Document doc, Elements contents, HashMap<String, String> outMap,
			String[] parsingContainer, String market, String pharseType)

	{
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
	}

}
