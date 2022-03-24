package com.aPlatform.controller.service.finance.SO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Object getTotalFindata(Model model) throws Exception
	{
		return financeRetvBOC.setTotalFindata();
	}
	@GetMapping(value = "/rank")
	public Object getRankdata(@RequestParam Map<String, String> map, Model model) throws Exception
	{
		return financeRetvBOC.getRankFindata(map);
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
	@PostMapping(value = "/test")
	public Object test(@RequestBody FinanceVO financeVO) throws Exception
	{
		String a = financeVO.getFin_code();
		return new String("test");
	}
}
