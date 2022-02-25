package com.aPlatform.controller.service.finance.VO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import lombok.Data;

@Data
public class FinanceDataMatrix
{
	private Map<String, Document> pageDOCMap = new HashMap<String, Document>();;
	private Map<String, String> marketURLMap = new HashMap<String, String>();
	private String[] innerArr = {"kospi" , "kosdaq"};
	public FinanceDataMatrix()
	{
		this.marketURLMap.put("kospi", "https://finance.naver.com/sise/sise_index.naver?code=KOSPI");
		this.marketURLMap.put("kosdaq", "https://finance.naver.com/sise/sise_index.naver?code=KOSDAQ");
		this.marketURLMap.put("main", "https://finance.naver.com/");
		this.marketURLMap.put("rankMC", "#_topItems4");
		this.marketURLMap.put("index", "#quotient");
		this.marketURLMap.put("buyer", ".dd");
		this.marketURLMap.put("image", ".graph img");
		this.marketURLMap.put("news", "..a");
	}
	public void setPageDOCMapByString(String input) throws IOException
	{
		this.pageDOCMap.put(input, Jsoup.connect(this.marketURLMap.get(input)).get());
	}
	public void setPageDOCMapByInnerArray() throws IOException
	{
		for (int i = 0; i < this.innerArr.length; i++)
		{
			this.pageDOCMap.put(this.innerArr[i], Jsoup.connect(this.marketURLMap.get(this.innerArr[i])).get());
		}
	}

}
