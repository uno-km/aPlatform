package com.aPlatform.controller.file.SO;

import javax.xml.ws.spi.http.HttpHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aPlatform.controller.service.finance.BO.FinanceRetvBO;
import com.aPlatform.controller.service.finance.model.ExcelData;
import com.aPlatform.utils.FileUnoUtils;

@Service
public class FileUploadBO
{
	@Autowired
	ExcelData ExcelData;
	@Autowired
	FinanceRetvBO financeRetvBO;
	public ResponseEntity<String> uploadExcelData(final MultipartFile file) throws Exception
	{
		return this.financeRetvBO.excelInsert(file);
	}
}
