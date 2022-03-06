package com.aPlatform.controller.user.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.user.VO.UserinfoOutVO;
import com.aPlatform.controller.user.VO.UserinfoVO;
import com.aPlatform.mappers.UserActiveMapper;
import com.unoCode.Validation;

@Service
public class LoginBO
{
	@Autowired
	UserActiveMapper userActiveMapper;

	public boolean registerUser(UserinfoVO UserinfoVO)
	{
		UserinfoOutVO tempVO = userActiveMapper.getUserInfo(UserinfoVO);
		if(Validation.isNullCheck(tempVO))
		{
			userActiveMapper.signUp(UserinfoVO);
			return true;
		}
		return false;
	}

	public boolean checkUser(UserinfoVO UserinfoVO)
	{
		UserinfoOutVO tempVO = userActiveMapper.getUserInfo(UserinfoVO);
		if(Validation.isNullCheck(tempVO))
		{
			return false;
		}
		if(!tempVO.getUser_id().equals(UserinfoVO.getUser_id()))
		{
			return false;
		}
		if(!tempVO.getUser_password().equals(UserinfoVO.getUser_password()))
		{
			return false;
		}
		return true;
	}

	public UserinfoOutVO signinUser(UserinfoVO UserinfoVO)
	{
		UserinfoOutVO outVO = userActiveMapper.getUserInfo(UserinfoVO);
		if(Validation.isNullCheck(outVO))
		{
			return null;
		}
		if(!outVO.getUser_id().equals(UserinfoVO.getUser_id()))
		{
			return null;
		}
		if(!outVO.getUser_password().equals(UserinfoVO.getUser_password()))
		{
			return null;
		}
		return outVO;
	}

	public boolean checkDuplId(UserinfoVO UserinfoVO)
	{
		List<UserinfoVO> list = new ArrayList<UserinfoVO>();
		list = userActiveMapper.loadAllUserInfo();
		System.out.println(list.toString());
		if(userActiveMapper.checkDuplicationId(UserinfoVO) == 0)
		{
			return true;
		}
		return false;
	}

}
