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
	public void insertExcelData(CommonOutVO outVO, File file)
	{
		ResultDTO result = new ResultDTO();
		outVO.setResultDTO(result);
		try
		{
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
		catch (FileNotFoundException e)
		{
			// TODO: handle exception
			result.setCodeMessage("500", "파일을 찾을 수 없습니다.");
			outVO.setError(e.getMessage());
		}
		catch (IOException e)
		{
			// TODO: handle exception
			result.setCodeMessage("500", "엑셀파일을 다루는 도중 오류가 발생했습니다..");
			outVO.setError(e.getMessage());
		}
		catch (Exception e)
		{
			// TODO: handle exception
			result.setCodeMessage("500", "알수없는 오류가 발생했습니다. 관리자에게 문의하세요.");
			outVO.setError(e.getMessage());
		}
	}
}
