package com.aPlatform.controller.service.finance.arch;

import java.util.HashMap;

import org.jsoup.nodes.Document;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

public interface SetFinanceData
{
	public Object excute(FinanceDataMatrix financeDataMatrix, Document doc, HashMap<String, String> outMap, String market, String pharseType);
}
