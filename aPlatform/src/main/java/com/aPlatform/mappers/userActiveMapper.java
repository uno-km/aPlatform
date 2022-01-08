package com.aPlatform.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aPlatform.controller.user.VO.UserinfoVO;

@Mapper
public interface userActiveMapper
{
	public UserinfoVO getUserInfo(UserinfoVO userinfoVO);

	public void signUp(UserinfoVO userinfoVO);

	public List<UserinfoVO> loadAllUserInfo();

	public int checkDuplicationId(UserinfoVO userinfoVO);
}
