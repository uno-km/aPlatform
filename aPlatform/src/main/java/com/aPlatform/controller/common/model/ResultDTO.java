package com.aPlatform.controller.common.model;

import lombok.Data;

@Data
public class ResultDTO
{
	public ResultDTO()
	{

	}
	public ResultDTO(final String code, final String message)
	{
		this.code = code;
		this.message = message;
	}
	public void setCodeMessage(final String code, final String message)
	{
		this.code = code;
		this.message = message;
	}
	String code;
	String message;
}
