package com.aPlatform.controller.file.SO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aPlatform.controller.service.finance.model.ExcelData;
import com.aPlatform.utils.FileUnoUtils;

@Service
public class FileUploadBO
{
	@Autowired
	ExcelData ExcelData;
	public ResponseEntity<String> uploadExcelData(final MultipartFile file) throws Exception
	{
		this.ExcelData.callExcel(FileUnoUtils.multipartFileToFile(file));
		return null;
	}
}
