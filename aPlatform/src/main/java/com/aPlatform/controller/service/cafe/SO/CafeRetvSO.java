package com.aPlatform.controller.service.cafe.SO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.service.cafe.BO.CafeRetvBOC;

@RestController
@RequestMapping(value = "/service/cafe")
public class CafeRetvSO
{
	@Autowired
	CafeRetvBOC cafeRetvBOC;

	@GetMapping(value = "/main")
	private ModelAndView reternMainPage(Model model)
	{
		return cafeRetvBOC.reternMainPage();
	}
	@PostMapping(value = "get")
	private Map<String, Object> get(@RequestBody Map<String, Object> innerMap)
	{
		return null;
	}
	@PostMapping(value = "getData")
	private Map<String, Object> getData(@RequestBody Map<String, Object> innerMap)
	{
		return null;
	}
	@PostMapping(value = "post")
	private Map<String, Object> post(@RequestBody Map<String, Object> innerMap)
	{
		return null;
	}
	@PostMapping(value = "postData")
	private Map<String, Object> postData(@RequestBody Map<String, Object> innerMap)
	{
		return null;
	}
	@PutMapping(value = "put")
	private Map<String, Object> put(@RequestBody Map<String, Object> innerMap)
	{
		return null;
	}
	@DeleteMapping(value = "delete")
	private Map<String, Object> delete(@RequestBody Map<String, Object> innerMap)
	{
		return null;
	}
}
