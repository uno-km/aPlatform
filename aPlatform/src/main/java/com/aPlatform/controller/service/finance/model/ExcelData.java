package com.aPlatform.controller.service.finance.model;

import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aPlatform.controller.service.finance.VO.FinanceVO;

@SuppressWarnings("resource")
@Service
public class ExcelData
{
	public List<FinanceVO> callExcel(final MultipartFile uploadFile) throws Exception
	{
		String path = System.getProperty("user.dir");
		String filename = "\\src\\data_4008_20220511.xlsx"; // 파일명 설정
		return this.readExcel(path, filename);
	}
	private List<FinanceVO> readExcel(String path, String filename) throws Exception
	{
		List<FinanceVO> outList = new ArrayList<FinanceVO>();
		FileInputStream file = new FileInputStream(path + filename);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		// HSSFWorkbook workbook = new HSSFWorkbook(file);
		NumberFormat f = NumberFormat.getInstance();
		f.setGroupingUsed(false); // 지수로 안나오게 설정
		// 시트 갯수
		int sheetNum = workbook.getNumberOfSheets();
		for (int s = 0; s < sheetNum; s++)
		{
			XSSFSheet sheet = workbook.getSheetAt(s);
			// 행 갯수
			int rows = sheet.getPhysicalNumberOfRows();
			for (int r = 1; r < rows; r++)
			{
				XSSFRow row = sheet.getRow(r);
				int cells = row.getPhysicalNumberOfCells();
				FinanceVO inVO = new FinanceVO();
				for (int c = 0; c < cells; c++)
					if(c == 1 || c == 3 || c == 6)
					{
						XSSFCell cell = row.getCell(c);
						String value = "";
						if(cell != null)
							// 타입 체크
							switch (cell.getCellType())
							{
							case STRING :
							value = cell.getStringCellValue();
							break;
							case NUMERIC :
							value = f.format(cell.getNumericCellValue()) + "";
							break;
							case BLANK :
							value = cell.getBooleanCellValue() + "";
							break;
							case ERROR :
							value = cell.getErrorCellValue() + "";
							break;
							default :
							break;
							}
						switch (c) {
							case 1 :
								inVO.setFinCode(value);
								break;
							case 3 :
								inVO.setFinName(value);
								break;
							case 6 :
								inVO.setExchage(value);
								;
								break;
						}
					}
				outList.add(inVO);
			}
		}
		return outList;
	}
}
