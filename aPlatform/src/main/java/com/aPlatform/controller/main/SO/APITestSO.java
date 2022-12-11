package com.aPlatform.controller.main.SO;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APITestSO
{
	@DeleteMapping(value = "/header")
	public String deleteHeader(@RequestHeader Map<String, Object> header)
	{
		return null;
	}
	@DeleteMapping(value = "/body")
	public String deleteBody(@RequestBody Map<String, String> param)
	{
		return null;
	}
	@DeleteMapping(value = "/param")
	public String deleteParam(@RequestParam Map<String, Object> param)
	{
		return null;
	}
	@DeleteMapping(value = "/delete/{path}")
	public String deletePath(@PathVariable(value = "path") String path)
	{
		return null;
	}
}
