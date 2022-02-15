package com.aPlatform.controller.service.finance.SO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.unoCode.GetURLInfo;

@RestController
@RequestMapping(value = "/service/finance")
public class FinanceRetvSO
{
	@GetMapping(value = "/main")
	private ModelAndView reternMainPage(Model model)
	{
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("finance/index");
		return modelandview;
	}
	@GetMapping(value = "/total")
	public ArrayList<Map<String, String>> getFindata(Model model)
			throws IOException
	{
		GetURLInfo getUrlInfo = new GetURLInfo();
		Map<String, String> inMap = new HashMap<String, String>();
		getUrlInfo.setMarketURLMap(inMap);
		ArrayList<Map<String, String>> outArr = new ArrayList<Map<String, String>>();
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kospi", "index"));
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kospi", "buyer"));
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kospi", "image"));
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kosdaq", "index"));
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kosdaq", "buyer"));
		outArr.add(getUrlInfo.getMarketIndex(inMap, "kosdaq", "image"));
		return outArr;
	}
}
