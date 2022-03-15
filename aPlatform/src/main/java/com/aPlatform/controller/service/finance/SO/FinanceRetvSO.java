package com.aPlatform.controller.service.finance.SO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.service.finance.BOC.FinanceRetvBOC;
import com.aPlatform.controller.service.finance.VO.FinanceVO;

@RestController
@RequestMapping(value = "/service/finance")
public class FinanceRetvSO
{
	@Autowired
	FinanceRetvBOC financeRetvBOC;
	@Autowired
	FinanceSearchBOC financeSearchBOC;
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
	public Map<String, ArrayList<String>> getRankdata(@RequestParam Map<String, String> map, Model model) throws IOException
	{
		return financeRetvBOC.getRankFindata(map);
	}
	@GetMapping(value = "/code")
	public List<FinanceVO> getCode(Model model, @RequestParam String name) throws IOException
	{
		return financeRetvBOC.getCode(name);
	}
	@GetMapping(value = "/codeAllMap")
	public Map<String, String> getCodeMap(Model model) throws IOException
	{
		return financeRetvBOC.getCodeMap();
	}
	@GetMapping(value = "/shareInfo")
	public Map<String, Object> getShareInfoDTL(Model model, @RequestParam Map<String, String> map) throws IOException
	{
		return financeSearchBOC.getInfoDTL(map);
	}
	@GetMapping(value = "/news")
	public List<List<String>> getNews(Model model, @RequestParam Map<String, String> map) throws IOException
	{
		return financeSearchBOC.getNews(map);
	}
}
