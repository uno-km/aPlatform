package com.aPlatform.controller.main.SO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aPlatform.controller.main.DAO.MaindataLoadDAO;

@Controller
@RequestMapping(value = "/dataload")
public class MaindataLoadSO
{
	@Autowired
	MaindataLoadDAO maindataLoadDAO;
	@PostMapping(value = "/navbar")
	public void serviceList()
	{
		System.out.println(maindataLoadDAO.loadNavbar());
	}
}
