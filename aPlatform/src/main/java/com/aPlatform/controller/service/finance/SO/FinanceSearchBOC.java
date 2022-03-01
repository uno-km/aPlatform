package com.aPlatform.controller.service.finance.SO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.BO.FinanceDetailBO;
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.model.GetURLInfo;
@Service
public class FinanceSearchBOC
{
	@Autowired
	FinanceDetailBO financeDetailBO;

	public Map<String, ArrayList<String>> getInfoDTL(String code) throws IOException
	{
		GetURLInfo getUrlInfo = new GetURLInfo();
		FinanceDataMatrix financeDataMatrix = new FinanceDataMatrix(code);
		financeDataMatrix.setPageDOCMapByString("infoDTL");
		return getUrlInfo.getMapStringArrayList(financeDataMatrix, "infoDTL", "detail");
	}
}
