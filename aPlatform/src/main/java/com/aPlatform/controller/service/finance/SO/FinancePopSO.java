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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.service.finance.BOC.FinanceRetvBOC;
import com.aPlatform.controller.service.finance.VO.FinanceDataMatrix;

@RestController
@RequestMapping(value = "/service/finance")
public class FinancePopSO
{
	@Autowired
	FinanceRetvBOC financeRetvBOC;
	@Autowired
	FinanceSearchBOC financeSearchBOC;
	@Autowired
	FinanceDataMatrix financeDataMatrix;

	@PutMapping(value = "/excel")
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	private ResponseEntity<String> execlDataFileInsert()
	{
		return this.financeRetvBOC.execlDataFileInsert();
	}
	@PutMapping(value = "/excelUpload")
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	private ResponseEntity<String> excelFileUpload()
	{
		return this.financeRetvBOC.execlDataFileInsert();
	}

}
