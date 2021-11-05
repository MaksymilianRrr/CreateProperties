package CreateProperties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XlsxReaderForProperty {
	
	static XSSFWorkbook workbook;
	static File file;
	static FileInputStream inputStream;
	static XSSFSheet sheet;
	static XSSFRow row;
	static FileOutputStream fileOut;
	
	public void readXlsx(String path) throws IOException {
		//Change file path
		file = new File(path);
		inputStream = new FileInputStream(file);
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheetAt(0);
		//this.path = path;
	}
	
	public static int getCount() {
		return sheet.getLastRowNum()+1;
	}
	
	public static String getName() {
		row = sheet.getRow(PropertyMain.position);
		return row.getCell(0).getStringCellValue();
	}
	
	public static String getVersion() {
		row = sheet.getRow(PropertyMain.position);
		CellType type = row.getCell(1).getCellType();
		String version;
		if (type==CellType.NUMERIC) {
			double v = row.getCell(1).getNumericCellValue();
			version = Double.toString(v);
		}
		else { 
			version = row.getCell(1).getStringCellValue();
		}
		return version;
	}
	
	public static String getDesc() {
		row = sheet.getRow(PropertyMain.position);
		return row.getCell(2).getStringCellValue();
	}
	
	public static boolean released() {
		row = sheet.getRow(PropertyMain.position);
		boolean released;
		try {
			String releasedValue = row.getCell(3).getStringCellValue();
			released = Boolean.parseBoolean(releasedValue);
		}
		catch (Exception e) {
			released = row.getCell(3).getBooleanCellValue();
		}
		return released;
	}
	
	public static boolean deprecated() {
		row = sheet.getRow(PropertyMain.position);
		boolean deprecated;
		try {
			String deprecatedValue = row.getCell(4).getStringCellValue();
			deprecated = Boolean.parseBoolean(deprecatedValue);
		}
		catch (Exception e) {
			deprecated = row.getCell(4).getBooleanCellValue();
		}
		return deprecated;
	}
	
	public static String getDefUnits() {
		row = sheet.getRow(PropertyMain.position);
		return row.getCell(5).getStringCellValue();
	}
	
	public static String getFieldType() {
		row = sheet.getRow(PropertyMain.position);
		return row.getCell(6).getStringCellValue();
	}
	
	public static String getDataType() {
		row = sheet.getRow(PropertyMain.position);
		return row.getCell(7).getStringCellValue();
	}
	
	public static String getTextAlign() {
		row = sheet.getRow(PropertyMain.position);
		return row.getCell(8).getStringCellValue();
	}
	
	public static String[] getValues() {
		String allValues = row.getCell(9).getStringCellValue();
		String[] temp = allValues.split("\\|");
		int i = 0;
		for(String p: temp) {
			temp[i]=p;
			i++;
		}
		return temp;
	}
	
	public static void addColumns() {
		sheet.getRow(0).createCell(10).setCellValue("ID");
		sheet.getRow(0).createCell(11).setCellValue("Comment");
		sheet.getRow(0).createCell(12).setCellValue("Exception");
	}
	
	public static void setID(int id) {
		row = sheet.getRow(PropertyMain.position);
		row.createCell(10).setCellValue(id);
	}
	
	public static void setResult(String result) {
		row = sheet.getRow(PropertyMain.position);
		row.createCell(11).setCellValue(result);
	}
	
	public static void setException(String ex) {
		row = sheet.getRow(PropertyMain.position);
		try {
			String oldEx = row.getCell(12).getStringCellValue();
			row.createCell(12).setCellValue(oldEx+"  |  "+ex);
		}
		catch (Exception e) {
			row.createCell(12).setCellValue(ex);
		}
	}
	
	public static void saveResult(String path2) throws IOException {
		addColumns();
		fileOut = new FileOutputStream(path2);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
	}
}