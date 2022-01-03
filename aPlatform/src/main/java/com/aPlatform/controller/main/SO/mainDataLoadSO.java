package com.aPlatform.controller.main.SO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/loadservice/")
public class maiaDataLoadSO {
	@PostMapping(value = "serviceList/")
	@ResponseBody
	public void serviceList() {
		System.out.println("check");
	}
}
