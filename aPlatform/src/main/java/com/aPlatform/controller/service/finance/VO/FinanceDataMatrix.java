package com.aPlatform.controller.service.finance.VO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.mappers.FinanceDataMapper;

import lombok.Data;

@Service
@Data
public class FinanceDataMatrix
{
	@Autowired
	private FinanceDataMapper financeDataMapper;
	private Map<String, Document> pageDOCMap = new HashMap<String, Document>();;
	private Map<String, String> marketURLMap = new HashMap<String, String>();
	private String[] innerArr = {"kospi" , "kosdaq" };

	public void setMarketURLMap(Map<String, String> map)
	{
		setMarketURLMap();
		if(map.containsKey("code"))
		{
			marketURLMap.put(map.get("url"), "https://finance.naver.com/item/main.naver?code=" + map.get("code"));
			marketURLMap.put(map.get("pharseType"), ".h_th2");
			marketURLMap.put("code", map.get("code"));
		}
	}
	public void setMarketURLMap()
	{
		List<FinanceVO> mappingUrl = financeDataMapper.getMappingUrl();
		for (FinanceVO innerUrl : mappingUrl)
			this.marketURLMap.put(innerUrl.getFinType(), innerUrl.getFinUrl());
		List<FinanceVO> mappingPharse = financeDataMapper.getMappingPharse();
		for (FinanceVO innerUrl : mappingPharse)
			this.marketURLMap.put(innerUrl.getFinType(), innerUrl.getFinPharse());
	}

	public void setPageDOC(String input) throws IOException
	{
		this.pageDOCMap.put(input, Jsoup.connect(this.marketURLMap.get(input)).get());
	}
	public void setPageDOC() throws IOException
	{
		for (int i = 0; i < this.innerArr.length; i++)
			this.pageDOCMap.put(this.innerArr[i], Jsoup.connect(this.marketURLMap.get(this.innerArr[i])).get());
	}
}
