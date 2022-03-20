package com.aPlatform.controller.user.SO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aPlatform.controller.user.BOC.LoginBOC;
import com.aPlatform.controller.user.VO.UserinfoOutVO;
import com.aPlatform.controller.user.VO.UserinfoVO;

@Controller
@RequestMapping(value = "/user")
public class UserActiveSO
{
	@Autowired
	LoginBOC loginBOC;
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public boolean register(@RequestBody UserinfoVO userinfoVO)
	{
		return loginBOC.registerUser(userinfoVO);
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/checkid")
	public boolean checkDuplId(@RequestParam String user_id)
	{
		UserinfoVO userinfoVO = new UserinfoVO();
		userinfoVO.setUser_id(user_id);
		return loginBOC.checkDuplId(userinfoVO);
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/signin")
	public ResponseEntity<UserinfoOutVO> signinUser(@RequestBody UserinfoVO userinfoVO)
	{
		UserinfoOutVO outVO = new UserinfoOutVO();
		outVO = loginBOC.signinUser(userinfoVO);
		return outVO != null ? new ResponseEntity<>(outVO, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
