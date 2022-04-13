package com.aPlatform.controller.service.finance.model;

public enum UrlMileStone
{
	INDEX("IndexSwitch"), BUYER("BuyerSwitch"), IMAGE("ImageSwitch"), RANKMC("RankSwitch"), DETAIL("DetailSwitch"), NEWS("NewsSwitch"), KOSPI(
			"KospiSwitch"), KOSDAQ("KosdaqSwitch");
	private final String value;
	private final String PACK_PATH = "com.aPlatform.controller.service.finance.model.";
	UrlMileStone(String value)
	{
		this.value = value;
	}
	public String getValue()
	{
		return this.PACK_PATH + this.value;
	}
}
