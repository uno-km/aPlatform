package com.aPlatform.controller.service.finance.model;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
@Service
public class GetURLInfo
{
	public Object getUrlInfoObject(final FinanceDataMatrix financeDataMatrix, final String url, final String pharseType) throws Exception
	{
		this.asd(financeDataMatrix, url, pharseType);
		return FinanceURL.pharsingURL(financeDataMatrix, url, pharseType);
	}
	public Object asd(FinanceDataMatrix financeDataMatrix, String url, String pharseType)
	{
		financeDataMatrix.setMarket(url);
		financeDataMatrix.setPharseType(pharseType);
		financeDataMatrix.setConnectedDoc(financeDataMatrix.getPageDOCMap().get(url));
		FinanceDataType financeDataType = new FinanceDataTypeFacotry().getFinDataType(pharseType);
		Object qw = financeDataType.excute(financeDataMatrix);
		return null;
	}
}