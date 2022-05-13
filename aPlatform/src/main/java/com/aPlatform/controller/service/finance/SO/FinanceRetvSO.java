package com.aPlatform.controller.service.finance.SO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

	@PutMapping(value = "/excel")
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE, propagation = Propagation.NEVER)
	private ResponseEntity<String> execlDataFileInsert()
	// private ResponseEntity<String> execlDataFileInsert(final MultipartFile uploadFile)
	{
		return this.financeRetvBOC.execlDataFileInsert();
	}
	@GetMapping(value = "/main")
	private ModelAndView reternMainPage(Model model)
	// private String reternMainPage(Model model)
	{
		return financeRetvBOC.reternMainPage();
	}
	@GetMapping(value = "/codeAllMap")
	public Map<String, String> getCodeMap(Model model) throws Exception
	{
		return financeRetvBOC.getCodeMap();
	}
	@PostMapping(value = "/getSendingData")
	public Map<String, String> getSendingData(@RequestBody final Map<String, Object> inputMap) throws Exception
	{
		return financeRetvBOC.getCodeMap();
	}
	@GetMapping(value = "/{dataform}")
	public Object getData(@PathVariable final String dataform, @RequestParam Map<String, Object> map) throws Exception
	{
		System.out.println("Client required " + dataform + " data...");
		return financeSearchBOC.getInfo(financeDataMatrix, dataform, map);
	}
}
