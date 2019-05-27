package com.technolog.chemprocess;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReportGenerator
{

	public static byte[] getXlsReport(ChemProcess process)
	{
		HSSFWorkbook reportBook = new HSSFWorkbook();
		HSSFSheet reportSheet = reportBook.createSheet("Chemical Process");

		int rowNum = 0;
		Row row = reportSheet.createRow(rowNum++);
		Cell nameCell = row.createCell(0);
		Cell valueCell = row.createCell(1);
		nameCell.setCellValue("Parameters");
		valueCell.setCellValue("Values");

		row = reportSheet.createRow(rowNum++);
		nameCell = row.createCell(0);
		valueCell = row.createCell(1);
		nameCell.setCellValue("Material Name");
		valueCell.setCellValue(process.getMaterialName());

		row = reportSheet.createRow(rowNum++);
		nameCell = row.createCell(0);
		valueCell = row.createCell(1);
		nameCell.setCellValue("Channel Width");
		valueCell.setCellValue(process.getChannelWidth());

		row = reportSheet.createRow(rowNum++);
		nameCell = row.createCell(0);
		valueCell = row.createCell(1);
		nameCell.setCellValue("Channel Height");
		valueCell.setCellValue(process.getChannelHeight());

		row = reportSheet.createRow(rowNum++);
		nameCell = row.createCell(0);
		valueCell = row.createCell(1);
		nameCell.setCellValue("Channel Length");
		valueCell.setCellValue(process.getChannelLength());

		row = reportSheet.createRow(rowNum++);
		nameCell = row.createCell(0);
		valueCell = row.createCell(1);
		nameCell.setCellValue("Speed of the Hood");
		valueCell.setCellValue(process.getHoodSpeed());

		row = reportSheet.createRow(rowNum++);
		nameCell = row.createCell(0);
		valueCell = row.createCell(1);
		nameCell.setCellValue("Initial Temperature of the Hood");
		valueCell.setCellValue(process.getHoodTemp());

		row = reportSheet.createRow(rowNum++);
		nameCell = row.createCell(0);
		valueCell = row.createCell(1);
		nameCell.setCellValue("Process Productivity");
		valueCell.setCellValue(process.getProductivity());

		row = reportSheet.createRow(rowNum++);
		nameCell = row.createCell(0);
		valueCell = row.createCell(1);
		nameCell.setCellValue("Density of the Product");
		valueCell.setCellValue(process.getConsistency());

		row = reportSheet.createRow(rowNum++);
		nameCell = row.createCell(0);
		valueCell = row.createCell(1);
		nameCell.setCellValue("Final Temperature of the Product");
		valueCell.setCellValue(process.getTemperature());

		return reportBook.getBytes();
	}
}
