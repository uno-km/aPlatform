package com.aPlatform.controller.service.finance.BOC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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

	private FinanceRetvBOC() throws Exception
	{
		this.outArr = new ArrayList<Map<String, String>>();
		this.getUrlInfo = new GetURLInfo();
		this.financeDataMatrix = new FinanceDataMatrix();
	}

	public List<FinanceVO> getCode(String name)
	{
		return financeRetvBO.getCode(name);
	}
	public Map<String, String> getCodeMap()
	{
		return financeRetvBO.getCodeMap();
	}
	public ModelAndView reternMainPage()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("finance/index");
		return mv;
	}
	@SuppressWarnings("unchecked")
	public Object setTotalFindata(String marketType) throws Exception
	{
		this.financeDataMatrix.setPageDOC();
		if(marketType.equalsIgnoreCase("total"))
		{
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "index"));
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "buyer"));
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "image"));
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "index"));
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "buyer"));
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "image"));
			return outArr;
		}
		if(marketType.equalsIgnoreCase("kosqi"))
		{
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "index"));
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "buyer"));
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kospi", "image"));
			return outArr;
		}
		else
		{
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "index"));
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "buyer"));
			outArr.add((Map<String, String>) getUrlInfo.getUrlInfoObject(financeDataMatrix, "kosdaq", "image"));
			return outArr;
		}
	}
}
