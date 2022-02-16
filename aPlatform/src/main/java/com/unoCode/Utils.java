package com.unoCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Utils
{
	public static Object pharsingURL(Map<String, String> marketURLMap, String market, String pharseType) throws IOException
	{
		Document doc = Jsoup.connect(marketURLMap.get(market)).get();
		if(pharseType.isEmpty()) pharseType = "rankMC";
		HashMap<String, String> outMap = new HashMap<String, String>();
		String[] parsingContainer;
		Elements contents = doc.select(marketURLMap.get(pharseType));
		switch (pharseType) {
			case "index" :
				String[] indexSub = {"_index" , "_per" , "_change" };
				parsingContainer = contents.text().split(" ");
				for (int i = 0; i < indexSub.length; i++)
				{
					outMap.put(market + indexSub[i], parsingContainer[i]);
				}
				break;
			case "buyer" :
				int bcnt = 0;
				String[] buyerSub = {"_ant" , "_org" , "_frg" };
				parsingContainer = contents.text().split(" ");
				for (int i = 1; i < parsingContainer.length; i++)
				{
					if(bcnt == 3)
					{
						break;
					}
					outMap.put(market + buyerSub[bcnt], parsingContainer[i]);
					i++;
					bcnt++;
				}
				break;
			case "image" :
				int icnt = 0;
				String[] isub = {"_day" , "_day90" , "_day365" , "_day1095" };
				parsingContainer = contents.toString().split("<img src=\"");
				for (int i = 0; i < parsingContainer.length; i++)
				{
					if(!parsingContainer[i].isEmpty())
					{
						outMap.put(market + isub[icnt], parsingContainer[i].split("\" alt=")[0]);
						icnt++;
					}
				}
				break;
			case "rankMC" :
				ArrayList<ArrayList<String>> outArr = new ArrayList<>();
				parsingContainer = contents.text().split(" ");
				for (int i = 0; i < parsingContainer.length; i++)
				{
					ArrayList<String> innerArr = new ArrayList<>();
					for (int innerCnt = 0; innerCnt < 5; innerCnt++)
					{
						if(parsingContainer[i + innerCnt].equals("보합"))
						{
							innerArr.add("0");
							innerArr.add(parsingContainer[i + innerCnt]);
							innerArr.add(parsingContainer[i + innerCnt + 1]);
							i--;
							break;
						}
						innerArr.add(parsingContainer[i + innerCnt]);
					}
					outArr.add(innerArr);
					i = i + 4;
				}
				return outArr;
		}
		return outMap;
	}
}
