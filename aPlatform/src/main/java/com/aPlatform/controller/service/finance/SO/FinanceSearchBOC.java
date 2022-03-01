package com.aPlatform.controller.service.finance.SO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aPlatform.controller.service.finance.BO.FinanceDetailBO;
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.model.GetURLInfo;

public class FinanceSearchBOC
{
	@Autowired
	FinanceDetailBO financeDetailBO;

	GetURLInfo getUrlInfo;

	public Map<String, ArrayList<String>> getInfoDTL(String code) throws IOException
	{
		FinanceDataMatrix financeDataMatrix = new FinanceDataMatrix(code);
		financeDataMatrix.setPageDOCMapByString("infoDTL");
		return getUrlInfo.getMapStringArrayList(financeDataMatrix, "infoDTL", "detail");
	}
}
