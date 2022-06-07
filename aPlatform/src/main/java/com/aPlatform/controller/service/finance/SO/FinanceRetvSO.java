package com.aPlatform.controller.service.finance.SO;

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
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

@RestController
@RequestMapping(value = "/service/finance")
public class FinanceRetvSO
{
	@Autowired
	FinanceRetvBOC financeRetvBOC;
	@Autowired
	FinanceSearchBOC financeSearchBOC;
	@Autowired
	FinanceDataMatrix financeDataMatrix;

	@GetMapping(value = "/main")
	private ModelAndView reternMainPage(Model model)
	{
		return financeRetvBOC.reternMainPage();
	}
	@GetMapping(value = "/codeAllMap")
	private Map<String, String> getCodeMap()
	{
		return this.financeRetvBOC.getCodeMap();
	}
	@GetMapping(value = "/excelUploadPopup")
	private ModelAndView returnPopupExcelUpload()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("finance/popup/excelUploadPopup");
		return mv;
	}
	@GetMapping(value = "/{dataform}")
	private Object getData(@PathVariable final String dataform, @RequestParam Map<String, Object> map) throws Exception
	{
		System.out.println("Client required " + dataform + " data...");
		return financeSearchBOC.getInfo(financeDataMatrix, dataform, map);
	}
}
