package com.aPlatform.controller.file.SO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/common/file")
public class FileUploadBO
{
	@RequestMapping(value = "/uploadFile")
	private ModelAndView returnPopupExcelUpload()
	{
		return null;
	}
}
