package com.unoCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
public class GetURLInfo
{
	public static void main(String[] args) throws IOException
	{
		HashMap<String, URL> map = new HashMap<String, URL>();
		map.put("kosdaq", new URL(
				"https://finance.naver.com/sise/sise_index.naver?code=KOSDAQ"));
		map.put("kospi", new URL(
				"https://finance.naver.com/sise/sise_index.naver?code=KOSPI"));
		// String points = "id=\"now_value\"";
		String points = "<dd class=\"dd\">";
		BufferedReader buff = new BufferedReader(new InputStreamReader(
				map.get("kosdaq").openConnection().getInputStream()));
		String line = buff.readLine();
		int cnt = 0;
		while (line != null)
		{
			if(line.contains(points) && cnt < 3)
			{
				System.out.println(
						line.split("<dd class=\"dd\">")[1].split("<span>")[0]
								.split("\">")[1]);
				cnt++;
			}
			line = buff.readLine();
		}
	}
}
// id="now_value" : 지수;
// https://ssl.pstatic.net/imgstock/chart3/day : img

// if(line.contains(points))
// {
// System.out.println(line.split(">")[1].split("<")[0]); //지수 포인트
// }

// 매매동향 이때 cnt가 필요함
// if(line.contains(points) && cnt < 3)
// {
// System.out.println(
// line.split("<dd class=\"dd\">")[1].split("<span>")[0]
// .split("\">")[1]);
// cnt++;
// }
