package com.aPlatform.controller.main.SO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aPlatform.controller.main.DAO.MaindataLoadDAO;
import com.aPlatform.controller.main.VO.MainDataLoadOutVO;

@RestController
@RequestMapping(value = "/dataload")
public class MaindataLoadSO
{
	@Autowired
	MaindataLoadDAO maindataLoadDAO;
	@GetMapping(value = "/navbar")
	public MainDataLoadOutVO serviceList(@RequestParam Map<String, String> param)
	{
		return maindataLoadDAO.loadNavbar(param);
	}
	@PostMapping(value = "/navbar2" )
	public MainDataLoadOutVO serviceList2(
			@RequestParam Map<String, String> param) throws Exception
	{
		return maindataLoadDAO.loadNavbar2(param);
	}
}