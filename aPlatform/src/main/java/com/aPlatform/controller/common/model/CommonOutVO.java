package com.aPlatform.controller.common.model;

import lombok.Data;

@Data
public class CommonOutVO
{
	private ResultDTO resultDTO;
	private String error;
	private Object returnResultDTO;
}
