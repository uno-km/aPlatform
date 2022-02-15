package com.unoCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utils
{
	public static Map<String, String> pharsingURL(BufferedReader buff,
			Map<String, String> marketURLMap, String market, String pharseType)
			throws IOException
	{
		String line = buff.readLine();
		HashMap<String, String> outMap = new HashMap<String, String>();
		switch (pharseType) {
			case "index" :
				while (line != null)
				{
					if(line.contains(marketURLMap.get(pharseType)))
					{
						outMap.put(market + "_" + pharseType,
								line.split(">")[1].split("<")[0]); // 지수 포인트
						return outMap;
					}
					line = buff.readLine();
				}
				break;
			case "buyer" :
				int cnt = 0;
				String[] sub = {"_ant" , "_org" , "_frg" };
				while (line != null)
				{
					if(line.contains(marketURLMap.get(pharseType)))
					{
						outMap.put(market + "_" + pharseType + sub[cnt],
								line.split("<dd class=\"dd\">")[1]
										.split("<span>")[0].split("\">")[1]);
						cnt++;
					}
					if(cnt == 3)
					{
						return outMap;
					}
					line = buff.readLine();
				}
				break;
			case "image" :
				int icnt = 0;
				String[] isub = {"_day" , "_day90" , "_day365" , "_day1095" };
				while (line != null)
				{
					if(line.contains(marketURLMap.get(pharseType)) && icnt < 4)
					{
						outMap.put(market + isub[icnt],
								line.split(
										"<div class=\"graph\"><img src=\"")[1]
												.split("\" alt=\"")[0]);
						icnt++;
					}
					if(icnt == 4)
					{
						return outMap;
					}
					line = buff.readLine();
				}
				break;
		}
		return outMap;
	}
}
