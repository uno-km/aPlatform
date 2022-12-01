package com.aPlatform.controller.service.finance.VO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.aPlatform.utils.FinanceConstants;

import lombok.Data;

@Service
@Data
public class FinanceDataMatrix
{
	private String pharseType;
	private String market;
	private Document connectedDoc;
	private Map<String, Document> pageDOCMap = new HashMap<String, Document>();
	private Map<String, String> marketURLMap = new HashMap<String, String>();
	private String[] innerArr = {FinanceConstants.KOSPI , FinanceConstants.KOSDAQ };

	public void setPageDOC(String input) throws IOException
	{
		if(!this.pageDOCMap.containsKey(input)) this.pageDOCMap.put(input, Jsoup.connect(this.marketURLMap.get(input)).get());
	}
	public void setPageDOC() throws IOException
	{
		for (String str : this.innerArr)
			if(!this.pageDOCMap.containsKey(str)) this.pageDOCMap.put(str, Jsoup.connect(this.marketURLMap.get(str)).get());
	}
	public final void clearMatrix()
	{
		this.pageDOCMap.clear();
		this.marketURLMap.clear();
	}
	public boolean isEmptyMarketURLMap()
	{
		if(this.marketURLMap.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void putMarketURLMap(String key, String value)
	{
		this.marketURLMap.put(key, value);
	}
}
