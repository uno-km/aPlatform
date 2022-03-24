package com.aPlatform.controller.service.finance.SO;

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

	public Object getInfo(Map<String, String> map) throws Exception
	{
		GetURLInfo getUrlInfo = new GetURLInfo();
		FinanceDataMatrix financeDataMatrix = new FinanceDataMatrix(map);
		String url = map.get("url");
		String pharseType = map.get("pharseType");
		financeDataMatrix.setPageDOC(url);
		return getUrlInfo.getUrlInfoObject(financeDataMatrix, url, pharseType);
	}
}
