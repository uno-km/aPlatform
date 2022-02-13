package com.aPlatform.controller.service.finance.SO;

import java.io.IOException;
import java.util.ArrayList;
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
	public ArrayList<Map<String,String>> getFindata(Model model) throws IOException
	{
		ArrayList<Map<String,String>> outArr =new ArrayList<Map<String,String>>();
		GetURLInfo getURLInfo = new GetURLInfo();
		outArr.add(getURLInfo.getMarketIndex("kospi", "index"));
		outArr.add(getURLInfo.getMarketIndex("kospi", "buyer"));
		outArr.add(getURLInfo.getMarketIndex("kosdaq", "index"));
		outArr.add(getURLInfo.getMarketIndex("kosdaq", "buyer"));
		return outArr;
	}
}
