package com.aPlatform.controller.service.finance.model;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public interface FinanceDataType
{
	public String getType();
	
	public abstract Object excute(FinanceDataMatrix financeDataMatrix);
}
