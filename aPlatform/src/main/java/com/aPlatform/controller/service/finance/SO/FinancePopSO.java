package com.aPlatform.controller.service.finance.SO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aPlatform.controller.service.finance.BOC.FinanceRetvBOC;

@RestController
@RequestMapping(value = "/service/finance")
public class FinancePopSO
{
	@Autowired
	FinanceRetvBOC financeRetvBOC;

	@PostMapping(value = "/excelUpload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE, propagation = Propagation.NEVER)
	private ResponseEntity<String> excelFileUpload(@RequestPart final MultipartFile file) throws Exception
	{
		return this.financeRetvBOC.execlDataFileInsert(file);
	}
}
