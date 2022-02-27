package com.aPlatform.controller.service.finance.SO;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aPlatform.controller.service.finance.BO.FinanceDetailBO;

public class FinanceSearchBOC
{
	@Autowired
	FinanceDetailBO financeDetailBO;

	public Map<String, Map<String, String>> getInfoDTL(String code)
	{
		Map<String, Map<String, String>> outMap = new HashMap<String, Map<String, String>>();
		financeDetailBO.setFinanceDetail(outMap);
		return outMap;
	}
}
