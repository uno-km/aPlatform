package com.aPlatform.controller.main.SO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aPlatform.controller.main.DAO.MaindataLoadDAO;
import com.aPlatform.controller.main.VO.NavbarVO;

@Controller
@RequestMapping(value = "/dataload")
public class MaindataLoadSO
{
	@Autowired
	MaindataLoadDAO maindataLoadDAO;
	@RequestMapping(method = RequestMethod.GET, value = "/navbar")
	public List<NavbarVO> serviceList(@RequestParam Map<String, String> param)
	{
		return maindataLoadDAO.loadNavbar(param);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/name")
	public Map<String, Object> getAgeByName(@RequestParam String inputName)
	{
		Map<String, Integer> ageMap = new HashMap<>();
		ageMap.put("tom", 10);
		ageMap.put("bob", 20);
		ageMap.put("kim", 30);

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("name", inputName);
		returnMap.put("age", ageMap.get(inputName));

		return returnMap;
	}

}