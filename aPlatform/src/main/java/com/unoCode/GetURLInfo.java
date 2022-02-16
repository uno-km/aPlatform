package com.unoCode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
@SuppressWarnings("unchecked")
public class GetURLInfo
{

	public void setMarketURLMap(Map<String, String> marketURLMap)
	{
		marketURLMap.put("kospi", "https://finance.naver.com/sise/sise_index.naver?code=KOSPI");
		marketURLMap.put("kosdaq", "https://finance.naver.com/sise/sise_index.naver?code=KOSDAQ");
		marketURLMap.put("main", "https://finance.naver.com/");
		marketURLMap.put("rankMC", "#_topItems4");
		marketURLMap.put("index", ".up");
		marketURLMap.put("buyer", ".dd");
		marketURLMap.put("image", ".graph img");
	}

	public Map<String, String> getMarketIndex(Map<String, String> marketURLMap, String url, String pharseType) throws IOException
	{
		return (Map<String, String>) Utils.pharsingURL(marketURLMap, url, pharseType);
	}

	public ArrayList<ArrayList<String>> getMarketRanking(Map<String, String> marketURLMap, String url) throws IOException
	{
		return (ArrayList<ArrayList<String>>) Utils.pharsingURL(marketURLMap, url, null);
	}
}