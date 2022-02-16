package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class test2
{
	
	
	Map<String, String> pharsingURL(Document doc, Map<String, String> marketURLMap, String market, String pharseType) throws IOException
	{
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
					System.out.println(contents.text().split(" ")[i]);
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
						System.out.println(parsingContainer[i].split("\" alt=")[0]);
						outMap.put(market + isub[icnt], parsingContainer[i].split("\" alt=")[0]);
						icnt++;
					}
				}
				break;
			default :
				System.out.println(doc);
				System.out.println(contents);
				String[] z = contents.toString().split("<img src=\"");
				for (int i = 1; i < z.length; i++)
				{
					if(!z[i].isEmpty())
					{
						System.out.println(z[i].split("\" alt=")[0]);
					}
				}
				break;
		}
		return outMap;
	}

	private void setMarketURLMap(Map<String, String> marketURLMap)
	{
		marketURLMap.put("kospi", "https://finance.naver.com/sise/sise_index.naver?code=KOSPI");
		marketURLMap.put("kosdaq", "https://finance.naver.com/sise/sise_index.naver?code=KOSDAQ");
		marketURLMap.put("main", "https://finance.naver.com/");
		marketURLMap.put("index", ".up");
		marketURLMap.put("buyer", ".dd");
		marketURLMap.put("image", ".graph img");
	}

	public Map<String, String> getMarketIndex(String market, String pharseType) throws IOException
	{
		if(null == pharseType) pharseType = "default";
		Map<String, String> marketURLMap = new HashMap<String, String>();
		setMarketURLMap(marketURLMap);
		Document doc = Jsoup.connect(marketURLMap.get(market)).get();
		return pharsingURL(doc, marketURLMap, market, pharseType);
	}
}

// if(line.contains(points) && cnt < 3)
// {
// System.out.println(
// line.split("<dd class=\"dd\">")[1].split("<span>")[0]
// .split("\">")[1]);
// cnt++;
// }
//// System.out.println(line);
// line = buff.readLine();
// }