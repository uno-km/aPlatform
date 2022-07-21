package com.aPlatform.controller.service.finance.SO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.service.finance.BOC.FinanceRetvBOC;
import com.aPlatform.controller.service.finance.BOC.FinanceSearchBOC;
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;
import com.aPlatform.controller.service.finance.VO.FinanceInDTO;

@RestController
@RequestMapping(value = "/service/finance")
public class FinanceRetvSO
{
	@Autowired
	private FinanceRetvBOC financeRetvBOC;
	@Autowired
	private FinanceSearchBOC financeSearchBOC;
	@Autowired
	private FinanceDataMatrix financeDataMatrix;

	@GetMapping(value = "/main")
	public ModelAndView reternMainPage(Model model)
	{
		return this.financeRetvBOC.reternMainPage();
	}

	@GetMapping(value = "/codeAllMap")
	public Map<String, String> getCodeMap()
	{
		return this.financeRetvBOC.getCodeMap();
	}
	@GetMapping(value = "/excelUploadPopup")
	public ModelAndView returnPopupExcelUpload()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("finance/popup/excelUploadPopup");
		return mv;
	}
	@PostMapping(value = "/data/{dataform}")
	public Object getDataPost(@PathVariable final String dataform, @RequestBody FinanceInDTO inDTO) throws Exception
	{
		System.out.println("Client required " + dataform + " data...");
		return this.financeSearchBOC.getInfo(financeDataMatrix, dataform, inDTO);
	}
}
