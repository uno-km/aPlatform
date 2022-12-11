package com.aPlatform.controller.main.SO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aPlatform.controller.main.BOC.MaindataLoadBOC;
import com.aPlatform.controller.main.VO.MainDataLoadOutVO;

@RestController
@RequestMapping(value = "/dataload")
public class MaindataLoadSO
{
	@Autowired
	MaindataLoadBOC maindataLoadDAO;
	@PostMapping(value = "/navbar", produces = {MediaType.APPLICATION_JSON_VALUE})
	public MainDataLoadOutVO serviceList(@RequestBody Map<String, String> param)
	{
		return maindataLoadDAO.loadNavbar(param);
	}
	@GetMapping(value = "/tw")
	public ModelAndView go2TestWeb()
	{
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("tw");
		modelandview.addObject("test", "ㅎㅇ");
		return modelandview;
	}
	@GetMapping(value = "/fileTest")
	public ModelAndView go2fileTestWeb()
	{
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("fileTest");
		return modelandview;
	}
}