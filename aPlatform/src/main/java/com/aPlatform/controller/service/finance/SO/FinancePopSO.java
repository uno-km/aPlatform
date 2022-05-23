package com.aPlatform.controller.service.finance.SO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aPlatform.controller.service.finance.BOC.FinanceRetvBOC;

@RestController
@RequestMapping(value = "/service/finance")
public class FinancePopSO
{
	@Autowired
	FinanceRetvBOC financeRetvBOC;
	@Autowired
	FinanceSearchBOC financeSearchBOC;

	@PutMapping(value = "/excelUpload")
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	private ResponseEntity<String> excelFileUpload()
	{
		return this.financeRetvBOC.execlDataFileInsert();
	}

}
