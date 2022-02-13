package com.unoCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
public class GetURLInfo
{
	private void setMarketURLMap(Map<String, String> marketURLMap)
	{
		marketURLMap.put("kospi",
				"https://finance.naver.com/sise/sise_index.naver?code=KOSPI");
		marketURLMap.put("kosdaq",
				"https://finance.naver.com/sise/sise_index.naver?code=KOSDAQ");
		marketURLMap.put("index", "id=\"now_value\"");
		marketURLMap.put("buyer", "<dd class=\"dd\">");
	}

	public Map<String, String> getMarketIndex(String market, String pharseType)
			throws IOException
	{
		Map<String, String> marketURLMap = new HashMap<String, String>();
		setMarketURLMap(marketURLMap);
		BufferedReader buff = new BufferedReader(
				new InputStreamReader(new URL(marketURLMap.get(market))
						.openConnection().getInputStream()));
		Utils.pharsingURL(buff, marketURLMap, market, pharseType);
		return null;
	}
}

// id="now_value" : 지수;
// https://ssl.pstatic.net/imgstock/chart3/day : img

// 매매동향 이때 cnt가 필요함
// if(line.contains(points) && cnt < 3)
// {
// System.out.println(
// line.split("<dd class=\"dd\">")[1].split("<span>")[0]
// .split("\">")[1]);
// cnt++;
// }
