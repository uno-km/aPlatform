package com.unoCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
public class GetURLInfo
{

	public void setMarketURLMap(Map<String, String> marketURLMap)
	{
		marketURLMap.put("kospi",
				"https://finance.naver.com/sise/sise_index.naver?code=KOSPI");
		marketURLMap.put("kosdaq",
				"https://finance.naver.com/sise/sise_index.naver?code=KOSDAQ");
		marketURLMap.put("index", "id=\"now_value\"");
		marketURLMap.put("buyer", "<dd class=\"dd\">");
		marketURLMap.put("image",
				"https://ssl.pstatic.net/imgstock/chart3/day");
	}

	public Map<String, String> getMarketIndex(Map<String, String> marketURLMap,
			String market, String pharseType) throws IOException
	{
		BufferedReader buff = new BufferedReader(
				new InputStreamReader(new URL(marketURLMap.get(market))
						.openConnection().getInputStream()));
		return Utils.pharsingURL(buff, marketURLMap, market, pharseType);
	}
}