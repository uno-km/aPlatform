package com.aPlatform.controller.service.finance.BOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.BO.FinanceRetvBO;
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.VO.FinanceVO;
import com.aPlatform.controller.service.finance.model.GetURLInfo;
@Service
public class FinanceRetvBOC
{
	@Autowired
	FinanceRetvBO financeRetvBO;

	ArrayList<Map<String, String>> outArr;
	GetURLInfo getUrlInfo;
	FinanceDataMatrix financeDataMatrix;

	private FinanceRetvBOC() throws IOException
	{
		this.outArr = new ArrayList<Map<String, String>>();
		this.getUrlInfo = new GetURLInfo();
		this.financeDataMatrix = new FinanceDataMatrix();
	}

	public ArrayList<Map<String, String>> setTotalFindata() throws IOException
	{
		this.financeDataMatrix.setPageDOCMapByInnerArray();
		outArr.add(getUrlInfo.getMarketIndex(financeDataMatrix, "kospi", "index"));
		outArr.add(getUrlInfo.getMarketIndex(financeDataMatrix, "kospi", "buyer"));
		outArr.add(getUrlInfo.getMarketIndex(financeDataMatrix, "kospi", "image"));
		outArr.add(getUrlInfo.getMarketIndex(financeDataMatrix, "kosdaq", "index"));
		outArr.add(getUrlInfo.getMarketIndex(financeDataMatrix, "kosdaq", "buyer"));
		outArr.add(getUrlInfo.getMarketIndex(financeDataMatrix, "kosdaq", "image"));
		return outArr;
	}
	public Map<String, ArrayList<String>> getRankFindata() throws IOException
	{
		this.financeDataMatrix.setPageDOCMapByString("main");
		return getUrlInfo.getMapStringArrayList(financeDataMatrix, "main", "rankMC");
	}
	public List<FinanceVO> getCode(String name)
	{
		return financeRetvBO.getCode(name);
	}
	public Map<String, String> getCodeMap()
	{
		return financeRetvBO.getCodeMap();
	}
}
