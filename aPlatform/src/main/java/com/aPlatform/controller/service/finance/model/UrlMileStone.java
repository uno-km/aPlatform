package com.aPlatform.controller.service.finance.model;

public enum UrlMileStone
{
	RANKMC("RankSwitch")
	, DETAIL("DetailSwitch")
	, NEWS("NewsSwitch")
	, KOSPI("KospiKosdaqSwitch")
	, KOSDAQ("KospiKosdaqSwitch")
	, ALLMARKET("KospiKosdaqSwitch")
	, ETCINDEX("EtcIndexSwitch");

	private final String value;
	private final String PACK_PATH = "com.aPlatform.controller.service.finance.model.";
	UrlMileStone(String value)
	{
		this.value = value;
	}
	UrlMileStone()
	{
		this.value = "";
	}
	public String getValue()
	{
		return this.PACK_PATH + this.value;
	}
}
