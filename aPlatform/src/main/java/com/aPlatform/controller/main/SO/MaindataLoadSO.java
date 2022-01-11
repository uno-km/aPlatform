package com.aPlatform.controller.main.SO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping(value = "/navbar"  ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public MainDataLoadOutVO serviceList(@RequestParam Map<String, String> param)
	{
		return maindataLoadDAO.loadNavbar(param);
	}
}