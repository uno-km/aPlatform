package com.aPlatform.controller.service.finance.SO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.service.finance.BOC.FinanceRetvBOC;
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

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
	private ModelAndView reternMainPage(Model model)
	{
		return this.financeRetvBOC.reternMainPage();
	}
	@PostMapping(value = "/main")
	private Map<String, String> getUserInterestShares()
	{
		return this.financeRetvBOC.getUserInterestShares();
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
		return this.financeSearchBOC.getInfo(financeDataMatrix, dataform, map);
	}
	@PostMapping(value = "/addUserInterest", produces = {MediaType.APPLICATION_JSON_VALUE })
	private CommonOutVO insertUserInterestShare(@RequestBody Map<String, String> param)
	{
		System.out.println("Client required insertUserInterestShare data...");
		return this.financeRetvBOC.insertUserInterestShare(param);
	}
	@PostMapping(value = "/getUserInterest")
	private CommonOutVO getUserInterestShare(@RequestBody Map<String, String> param)
	{
		return this.financeRetvBOC.insertUserInterestShare(param);
	}
}
