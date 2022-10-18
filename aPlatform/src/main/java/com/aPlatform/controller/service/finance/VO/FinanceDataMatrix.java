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
import com.aPlatform.utils.FinanceUtils;

import lombok.Data;

@Service
@Data
public class FinanceDataMatrix
{
	@Autowired
	private FinanceDataMapper financeDataMapper;
	private Map<String, Document> pageDOCMap = new HashMap<String, Document>();
	private Map<String, String> marketURLMap = new HashMap<String, String>();
	private String[] innerArr = {FinanceUtils.KOSPI , FinanceUtils.KOSDAQ };

	public void setMarketURLMap(FinanceInDTO inDTO)
	{
		setMarketURLMap();
		if(inDTO.getCode() != null)
		{
			marketURLMap.put(FinanceUtils.CODE, inDTO.getCode());
			marketURLMap.put(inDTO.getUrl(), FinanceUtils.DETAIL_URL + inDTO.getCode());
			marketURLMap.put(inDTO.getPharseType(), FinanceUtils.DETAIL_PHARSETYPE);
		}
	}
	public void setMarketURLMap()
	{
		if(this.marketURLMap.isEmpty())
		{
			List<FinanceVO> mappingUrl = financeDataMapper.getMappingUrl();
			for (FinanceVO innerUrl : mappingUrl)
				this.marketURLMap.put(innerUrl.getFinType(), innerUrl.getFinUrl());
			List<FinanceVO> mappingPharse = financeDataMapper.getMappingPharse();
			for (FinanceVO innerUrl : mappingPharse)
				this.marketURLMap.put(innerUrl.getFinType(), innerUrl.getFinPharse());
		}
	}

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
}
