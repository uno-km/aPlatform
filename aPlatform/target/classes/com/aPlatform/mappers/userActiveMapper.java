package com.uPlatform.controller.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.uPlatform.controller.DTO.UserVO;

@Mapper
public interface userActiveMapper
{
	public UserVO getUserInfo(UserVO userVO);

	public void signUp(UserVO userVO);

	public int checkDuplicationId(UserVO userVO);
}
