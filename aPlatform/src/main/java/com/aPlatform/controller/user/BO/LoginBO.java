package com.aPlatform.controller.user.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.common.model.ResultDTO;
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

	public CommonOutVO signinUser(UserinfoVO UserinfoVO)
	{
		CommonOutVO commonoutVO = new CommonOutVO();
		ResultDTO result = new ResultDTO();
		commonoutVO.setResultDTO(result);
		try
		{
			UserinfoOutVO outVO = userActiveMapper.getUserInfo(UserinfoVO);
			result.setCode("200");
			result.setMessage("로그인 성공!");
			if(Validation.isNullCheck(outVO))
			{
				result.setCode("500");
				result.setMessage("입력하신 정보가 없습니다! 다시 시도해주세요!");
			}
			if(!outVO.getUser_id().equals(UserinfoVO.getUser_id()))
			{
				result.setCode("500");
				result.setMessage("입력하신 아이디정보가 없습니다! 다시 시도해주세요!");
			}
			if(!outVO.getUser_password().equals(UserinfoVO.getUser_password()))
			{
				result.setCode("500");
				result.setMessage("입력하신 정보가 없습니다! 다시 시도해주세요!");
			}
			commonoutVO.setReturnResultDTO(outVO);
		}
		catch (Exception e)
		{
			result.setCode("500");
			result.setMessage("입력하신 정보가 없습니다! 다시 시도해주세요!");
			commonoutVO.setError(e.getMessage());
		}
		return commonoutVO;
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
