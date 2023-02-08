package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.arch.SuperFinance;
import com.aPlatform.utils.Constants;

public class KospiKosdaqSwitch extends SuperFinance implements FinanceDataType
{
	@SuppressWarnings("unchecked")
	@Override
	public Object excute(FinanceDataMatrix financeDataMatrix)
	{
		super.market = financeDataMatrix.getMarket();
		HashMap<String, String> outMap = new HashMap<String, String>();
		super.doc = financeDataMatrix.getConnectedDoc();
		super.contents = super.doc.select(financeDataMatrix.getMarketURLMap().get("index"));
		ArrayList<Map<String, String>> kosdaqArr = new ArrayList<Map<String, String>>();
		/* 하루 움직임 동향 시작 */
		String[] indexSub = {"_index", "_per", "_change"};
		super.parsingContainer = contents.text().split(" ");
		for (int i = 0; i < indexSub.length; i++) outMap.put(super.market + indexSub[i], super.parsingContainer[i]);
		kosdaqArr.add((Map<String, String>) outMap.clone());
		outMap.clear();
		/* 하루 움직임 동향 종료 */
		/* 매매주체 시작 */
		int bcnt = 0;
		super.contents = super.doc.select(financeDataMatrix.getMarketURLMap().get("buyer")).select("span");
		String[] buyerSub = {"_ant", "_org", "_frg"};
		for (int i = 0; i < super.contents.size(); i++)
		{
			if (bcnt == 3) break;
			if (i % 2 == 0)
			{
				outMap.put(super.market + buyerSub[bcnt], super.contents.get(i).text());
				bcnt++;
			}
		}
		kosdaqArr.add((Map<String, String>) outMap.clone());
		outMap.clear();
		/* 매매주체 종료 */
		/* 차트 이미지 시작 */
		super.contents = doc.select(financeDataMatrix.getMarketURLMap().get("image"));
		String[] isub = {"_day", "_day90", "_day365", "_day1095"};
		super.parsingContainer = super.contents.toString().split("<img src=\"");
		for (int i = 0; i < super.contents.size(); i++) outMap.put(super.market + isub[i], super.contents.get(i).attr("src"));
		kosdaqArr.add((Map<String, String>) outMap.clone());
		/* 차트 이미지 종료 */
		return kosdaqArr;
	}
	
	@Override
	public String getType()
	{
		return Constants.KOSPI_KOSDAQ_URL;
	}
}
