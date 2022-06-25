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

	public CommonOutVO registerUser(UserinfoVO UserinfoVO)
	{
		CommonOutVO commonoutVO = new CommonOutVO();
		ResultDTO result = new ResultDTO();
		commonoutVO.setResultDTO(result);
		UserinfoOutVO tempVO = userActiveMapper.getUserInfo(UserinfoVO);
		if(Validation.isNullCheck(tempVO))
		{
			userActiveMapper.signUp(UserinfoVO);
			result.setCode("200");
			result.setMessage("회원가입이 정상적으로 완료되었습니다.");
		}
		else
		{
			result.setCode("500");
			result.setMessage("알 수 없는 오류가 발생했습니다. 아이디와 비밀번호를 확인해 주세요.");
		}
		return commonoutVO;
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

	public CommonOutVO checkDuplId(UserinfoVO UserinfoVO)
	{
		CommonOutVO commonoutVO = new CommonOutVO();
		ResultDTO result = new ResultDTO();
		commonoutVO.setResultDTO(result);
		List<UserinfoVO> list = new ArrayList<UserinfoVO>();
		list = userActiveMapper.loadAllUserInfo();
		System.out.println(list.toString());
		if(userActiveMapper.checkDuplicationId(UserinfoVO) == 0)
		{
			result.setCode("200");
			result.setMessage("사용하실 수 있는 아이디입니다.");
		}
		else
		{
			result.setCode("500");
			result.setMessage("사용하실 수 없는 아이디입니다.\n사유 : 중복");
		}
		return commonoutVO;
	}

}
