package com.technolog.chemprocess;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReportGenerator
{

	public static HSSFWorkbook getXlsReport(ChemProcess process)
	{
		HSSFWorkbook reportBook = new HSSFWorkbook();
		HSSFSheet reportSheet = reportBook.getSheet("Chemical Process");

		int rowNum = 0;
		Row row = reportSheet.getRow(rowNum++);
		Cell nameCell = row.getCell(0);
		Cell valueCell = row.getCell(1);
		nameCell.setCellValue("Parameters");
		valueCell.setCellValue("Values");

		row = reportSheet.getRow(rowNum++);
		nameCell = row.getCell(0);
		valueCell = row.getCell(1);
		nameCell.setCellValue("Channel Width");
		valueCell.setCellValue(process.getChannelWidth());

		row = reportSheet.getRow(rowNum++);
		nameCell = row.getCell(0);
		valueCell = row.getCell(1);
		nameCell.setCellValue("Channel Height");
		valueCell.setCellValue(process.getChannelHeight());

		row = reportSheet.getRow(rowNum++);
		nameCell = row.getCell(0);
		valueCell = row.getCell(1);
		nameCell.setCellValue("Channel Length");
		valueCell.setCellValue(process.getChannelLength());

		row = reportSheet.getRow(rowNum++);
		nameCell = row.getCell(0);
		valueCell = row.getCell(1);
		nameCell.setCellValue("Speed of the Hood");
		valueCell.setCellValue(process.getHoodSpeed());

		row = reportSheet.getRow(rowNum++);
		nameCell = row.getCell(0);
		valueCell = row.getCell(1);
		nameCell.setCellValue("Initial Temperature of the Hood");
		valueCell.setCellValue(process.getHoodTemp());

		row = reportSheet.getRow(rowNum++);
		nameCell = row.getCell(0);
		valueCell = row.getCell(1);
		nameCell.setCellValue("Process Productivity");
		valueCell.setCellValue(process.getProductivity());

		row = reportSheet.getRow(rowNum++);
		nameCell = row.getCell(0);
		valueCell = row.getCell(1);
		nameCell.setCellValue("Density of the Product");
		valueCell.setCellValue(process.getDensity());

		row = reportSheet.getRow(rowNum++);
		nameCell = row.getCell(0);
		valueCell = row.getCell(1);
		nameCell.setCellValue("Final Temperature of the Product");
		valueCell.setCellValue(process.getTemperature());

		return reportBook;
	}
}
