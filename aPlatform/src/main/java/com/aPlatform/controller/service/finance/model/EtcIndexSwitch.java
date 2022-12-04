package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.arch.SuperFinance;
import com.aPlatform.utils.Constants;

public class EtcIndexSwitch extends SuperFinance implements UrlFactory
{
	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix)
	{
		HashMap<String, ArrayList<String>> outListMap = new HashMap<>();
		ArrayList<String> exChangeList = new ArrayList<String>();
		ArrayList<String> interestList = new ArrayList<String>();
		ArrayList<String> oilList = new ArrayList<String>();
		ArrayList<String> elementsList = new ArrayList<String>();
		super.doc = financeDataMatrix.getConnectedDoc();
		super.contents = super.doc.select(financeDataMatrix.getMarketURLMap().get(financeDataMatrix.getPharseType()));
		String[] Exc = super.contents.select(Constants.TABLE).get(0).select(Constants.TBODY).text().split(Constants.STR_ONE_SPACE);
		for (String input : Exc)
			if(!input.contains(Constants.STR_BRACKET_L)) exChangeList.add(input);
		outListMap.put("exChange", exChangeList);
		String[] Interest = super.contents.select(Constants.TABLE).get(2).select(Constants.TBODY).text().split(Constants.STR_ONE_SPACE);
		for (String input : Interest)
			interestList.add(input);
		outListMap.put("interest", interestList);
		String[] Oil = super.contents.select(Constants.TABLE).get(3).select(Constants.TBODY).text().split(Constants.STR_ONE_SPACE);
		for (String input : Oil)
			oilList.add(input);
		outListMap.put("oil", oilList);
		String[] Elements = super.contents.select(Constants.TABLE).get(5).select(Constants.TBODY).text().split(Constants.STR_ONE_SPACE);
		for (String input : Elements)
			elementsList.add(input);
		outListMap.put("elements", elementsList);
		return outListMap;
	}
}