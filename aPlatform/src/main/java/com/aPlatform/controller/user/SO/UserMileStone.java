package com.aPlatform.controller.user.SO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping(value = "")
public class UserMileStone
{
	@GetMapping("/all")
	public void doAll()
	{
		log.info("logined member");
	}
	@GetMapping("/member")
	public void doMember()
	{
		log.info("member member");
	}
	@GetMapping("/manager")
	public void doManager()
	{
		log.info("manager member");
	}
}
