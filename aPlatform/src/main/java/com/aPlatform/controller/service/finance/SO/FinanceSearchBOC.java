package com.aPlatform.controller.service.finance.SO;

import java.io.IOException;
import java.util.List;
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

	public List<List<String>> getInfoDTL(Map<String, String> map) throws IOException
	{
		GetURLInfo getUrlInfo = new GetURLInfo();
		FinanceDataMatrix financeDataMatrix = new FinanceDataMatrix(map);
		String url = map.get("url");
		String pharseType = map.get("pharseType");
		financeDataMatrix.setPageDOC(url);
		return getUrlInfo.getListArrayList(financeDataMatrix, url, pharseType);
	}
}
