package com.aPlatform.controller.service.finance.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aPlatform.controller.common.model.CommonOutVO;
import com.aPlatform.controller.common.model.ResultDTO;
import com.aPlatform.controller.service.finance.VO.FinanceVO;
import com.aPlatform.mappers.FinanceDataMapper;

@Service
@SuppressWarnings("resource")
public class ExcelData
{
	@Autowired
	FinanceDataMapper financeDataMapper;
	public void insertExcelData(CommonOutVO outVO, File file) throws Exception
	{
		ResultDTO result = new ResultDTO();
		outVO.setResultDTO(result);
		XSSFWorkbook excelWorkBook = new XSSFWorkbook(file);
		for (int sheetIdx = 0; sheetIdx < excelWorkBook.getNumberOfSheets(); sheetIdx++)
		{
			XSSFSheet excelSheet = excelWorkBook.getSheetAt(sheetIdx);
			// 행 갯수
			for (int rowIdx = 1; rowIdx < excelSheet.getPhysicalNumberOfRows(); rowIdx++)
			{
				XSSFRow excelRow = excelSheet.getRow(rowIdx);
				FinanceVO inVO = new FinanceVO();
				for (int cellIdx = 0; cellIdx < excelRow.getPhysicalNumberOfCells(); cellIdx++)
				{
					XSSFCell excelCell = excelRow.getCell(cellIdx);
					if(excelCell != null)
					{
						switch (excelCell.getColumnIndex()) {
							case 2 :
								inVO.setFinCode(excelCell.getStringCellValue());
								break;
							case 4 :
								inVO.setFinName(excelCell.getStringCellValue());
								break;
							case 6 :
								inVO.setExchange(excelCell.getStringCellValue());
								break;
						}
					}
				}
				this.financeDataMapper.insertSharesInfo(inVO);
			}
		}
		result.setCodeMessage("200", "새로운 주식종목등록이 완료되었습니다.");
	}
}
