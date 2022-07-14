package com.aPlatform.controller.service.finance.SO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.service.finance.BOC.FinanceRetvBOC;
import com.aPlatform.controller.service.finance.VO.FinanceInDTO;

@RestController
@RequestMapping(value = "/service/finance/interest")
public class FinanceInterestSO
{
	@Autowired
	private FinanceRetvBOC financeRetvBOC;
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE })
	public CommonOutVO insertUserInterestShare(@RequestBody FinanceInDTO inDTO)
	{
		System.out.println("Client required insertUserInterestShare data...");
		return this.financeRetvBOC.insertUserInterestShare(inDTO);
	}
	@GetMapping
	public CommonOutVO getUserInterestShares()
	{
		return this.financeRetvBOC.getUserInterestShares();
	}
	@PutMapping
	public CommonOutVO putUserInterestShares()
	{
		return null;
	}
	@DeleteMapping
	public CommonOutVO deleteUserInterestShares()
	{
		return null;
	}
}
