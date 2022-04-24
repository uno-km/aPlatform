package com.aPlatform.controller.service.finance.model;
import org.springframework.stereotype.Service;
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
@Service
public class GetURLInfo
{
	public Object getUrlInfoObject(final FinanceDataMatrix financeDataMatrix, final String url, final String pharseType) throws Exception
	{
		return FinanceURL.pharsingURL(financeDataMatrix, url, pharseType);
	}
}