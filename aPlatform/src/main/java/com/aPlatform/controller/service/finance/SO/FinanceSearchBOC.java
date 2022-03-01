package com.aPlatform.controller.service.finance.SO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aPlatform.controller.service.finance.BO.FinanceDetailBO;

public class FinanceSearchBOC
{
	@Autowired
	FinanceDetailBO financeDetailBO;

	public Map<String, List<String>> getInfoDTL(String code)
	{
		Map<String, List<String>> outMap = new LinkedHashMap<String, List<String>>();
		financeDetailBO.setFinanceDetail(outMap);
		return outMap;
	}
}
