package com.aPlatform.controller.service.finance.BOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.BO.FinanceRetvBO;
import com.aPlatform.controller.service.finance.VO.FinanceVO;
import com.unoCode.GetURLInfo;
@Service
public class FinanceRetvBOC
{
	@Autowired
	FinanceRetvBO financeRetvBO;
	public ArrayList<Map<String, String>> setTotalFindata() throws IOException
	{
		Map<String, String> inMap = new HashMap<String, String>();
		GetURLInfo getUrlInfo = new GetURLInfo();
		getUrlInfo.setMarketURLMap(inMap);
		ArrayList<Map<String, String>> outArr = new ArrayList<Map<String, String>>();
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kospi", "index"));
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kospi", "buyer"));
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kospi", "image"));
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kosdaq", "index"));
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kosdaq", "buyer"));
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kosdaq", "image"));
		return outArr;
	}
	public List<FinanceVO> getCode()
	{
		return financeRetvBO.getCode();
	}
}
