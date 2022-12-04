package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.arch.SuperFinance;
import com.aPlatform.utils.Constants;

public class RankSwitch extends SuperFinance implements UrlFactory
{

	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix)
	{
		super.doc = financeDataMatrix.getConnectedDoc();
		super.contents = super.doc.select(financeDataMatrix.getMarketURLMap().get(financeDataMatrix.getPharseType()));
		Map<String, ArrayList<String>> outListMap = new LinkedHashMap<>();
		parsingContainer = super.contents.text().split(" ");
		int rankCnt = 0;
		for (int i = 0; i < super.parsingContainer.length; i++)
		{
			ArrayList<String> innerArr = new ArrayList<>();
			for (int innerCnt = 0; innerCnt < 5; innerCnt++)
			{
				if(super.parsingContainer[i + innerCnt].equals(Constants.STEADY))
				{
					innerArr.add("0");
					innerArr.add(super.parsingContainer[i + innerCnt]);
					innerArr.add(super.parsingContainer[i + innerCnt + 1]);
					i--;
					break;
				}
				innerArr.add(super.parsingContainer[i + innerCnt]);
			}
			outListMap.put(Integer.toString(rankCnt), innerArr);
			rankCnt++;
			i = i + 4;
		}
		return outListMap;
	}

}