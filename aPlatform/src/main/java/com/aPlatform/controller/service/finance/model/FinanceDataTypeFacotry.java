package com.aPlatform.controller.service.finance.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FinanceDataTypeFacotry
{
	private Map<String, FinanceDataType> finDtTypeCache = new HashMap<>();
	@Autowired
	FinanceDataTypeFacotry()
	{
		List<	> financeDataTypes = new ArrayList<>();
		for (FinanceDataType financeDataType : financeDataTypes)
		{
			this.finDtTypeCache.put(financeDataType.getType(), financeDataType);
		}
	}
	
	public FinanceDataType getFinDataType(String type)
	{
		return finDtTypeCache.get(type);
	}
}
