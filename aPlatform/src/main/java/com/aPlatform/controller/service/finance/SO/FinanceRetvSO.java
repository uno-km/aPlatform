package com.aPlatform.controller.service.finance.SO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return financeRetvBOC.reternMainPage();
	}
	@GetMapping(value = "/market/{marketType}")
	public Object getTotalFindata(Model model, @PathVariable String marketType) throws Exception
	{
		return financeRetvBOC.setTotalFindata(marketType);
	}
	@GetMapping(value = "/rank")
	public Object getRankdata(@RequestParam Map<String, String> map, Model model) throws Exception
	{
		return financeSearchBOC.getInfo(map);
	}
	@GetMapping(value = "/code")
	public List<FinanceVO> getCode(Model model, @RequestParam String name) throws Exception
	{
		return financeRetvBOC.getCode(name);
	}
	@GetMapping(value = "/codeAllMap")
	public Map<String, String> getCodeMap(Model model) throws Exception
	{
		return financeRetvBOC.getCodeMap();
	}
	@GetMapping(value = "/shareInfo")
	public Object getShareInfoDTL(Model model, @RequestParam Map<String, String> map) throws Exception
	{
		return financeSearchBOC.getInfo(map);
	}
	@GetMapping(value = "/news")
	public Object getNews(Model model, @RequestParam Map<String, String> map) throws Exception
	{
		return financeSearchBOC.getInfo(map);
	}
}
