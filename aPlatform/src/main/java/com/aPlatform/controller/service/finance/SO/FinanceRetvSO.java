package com.aPlatform.controller.service.finance.SO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.service.finance.BOC.FinanceRetvBOC;
import com.aPlatform.controller.service.finance.VO.FinanceVO;
import com.unoCode.GetURLInfo;

@RestController
@RequestMapping(value = "/service/finance")
public class FinanceRetvSO
{
	@Autowired
	FinanceRetvBOC financeRetvBOC;
	@GetMapping(value = "/main")
	private ModelAndView reternMainPage(Model model)
	{
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("finance/index");
		return modelandview;
	}
	@GetMapping(value = "/total")
	public ArrayList<Map<String, String>> getTotalFindata(Model model) throws IOException
	{
		return financeRetvBOC.setTotalFindata();
	}
	@GetMapping(value = "/rank")
	public Map<String, ArrayList<String>> getRankdata(Model model) throws IOException
	{
		Map<String, String> inMap = new HashMap<String, String>();
		GetURLInfo getUrlInfo = new GetURLInfo();
		getUrlInfo.setMarketURLMap(inMap);
		return getUrlInfo.getMarketRanking(inMap, "main");
	}
	@GetMapping(value = "/code")
	public List<FinanceVO> getCode(Model model) throws IOException
	{
		return financeRetvBOC.getCode();
	}
}
