package com.itcast.dw.test.mobile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONObject;

public class MobileAddress {

	public static void main(String[] args) throws InvalidFormatException, IOException {

		String xpath = "http://api.online-service.vip/phone?number=";

		String mobileFile = "C:\\Users\\20190322\\Desktop\\预约-南山-135596.xlsx";
		//String mobileFile = "C:\\Users\\20190322\\Desktop\\次数统计(1).xlsx";
		//String newFileFoulder = "C:\\Users\\20190322\\Desktop\\深圳市号码";
		String newFileFoulder = "C:\\Users\\20190322\\Desktop\\南山登记所后一位";
		//String newMobileFile = "C:\\Users\\20190322\\Desktop\\深圳市号码\\深圳市号码";
		String newMobileFile = "C:\\Users\\20190322\\Desktop\\南山登记所后一位\\深圳市号码";

		File file = new File(mobileFile);
		Workbook wb = WorkbookFactory.create(new FileInputStream(file));

		File foulder = new File(newFileFoulder);
		if (!foulder.exists()) {
			foulder.mkdirs();
		}
		
		int fileNumber = 1;
		File newFile = new File(newMobileFile + fileNumber + ".xlsx");
		if (!newFile.exists()) {
			newFile.createNewFile();
		}
		
		FileOutputStream fileOutputStream = new FileOutputStream(newFile);

		Sheet sheet = null;
		Row row = null;
		Sheet newSheet = null;
		Row newRow = null;
		if (wb != null) {
			// 获取第一个sheet
			sheet = wb.getSheetAt(0);
			// 获取最大行数
			int rownum = sheet.getPhysicalNumberOfRows();
			// 获取第一行为Title?
			row = sheet.getRow(0);

			Set<String> mobileNumber = new HashSet<String>();

			for (int i = 1; i < rownum; i++) {
				row = sheet.getRow(i);
				// 取第一列的值，默认为string
				if (row != null && row.getCell(0) != null) {
					String mobile = getCellValueByCell(row.getCell(0));
					// 将后两位替换为00
					if (checkMobileNumber(mobile)) {
						String nineNumber = mobile.substring(0, 10);
						mobileNumber.add(nineNumber);
					}

				}
				
			}
			
			System.out.println("去重后总数：" + mobileNumber.size());
			
			//分文件导出
			int newRowNumber = 0;
			int newSheetNumber = 0;
			XSSFWorkbook newWb = new XSSFWorkbook();
			newSheet = createSheet(newWb,"sheet"+newSheetNumber);
			newSheetNumber++;
			newRow = createRow(newSheet, newRowNumber);
			newRowNumber++;
			createCell(newRow, 0).setCellValue("电话号码");
			
			for(String nineNumber:mobileNumber){
				String mobile = nineNumber + "0";
				System.out.println(mobile);

				JSONObject jsonResult = getHttp(xpath + mobile);

				if ("0755".equals(jsonResult.get("areacode"))) {
					if(newRowNumber > 100000){
						//写出
						newWb.write(fileOutputStream);
						fileNumber++;
						//重新创建文件
						newFile = new File(newMobileFile + fileNumber + ".xlsx");
						if (!newFile.exists()) {
							newFile.createNewFile();
						}
						fileOutputStream = new FileOutputStream(newFile);
						newWb = new XSSFWorkbook();
						newSheetNumber = 0;
						newRowNumber = 0;
						newSheet = createSheet(newWb,"sheet"+newSheetNumber);
						newSheetNumber++;
						newRow = createRow(newSheet, newRowNumber);
						newRowNumber++;
						createCell(newRow, 0).setCellValue("电话号码");
					}
					
					newRow = createRow(newSheet, newRowNumber);
					newRowNumber++;
					createCell(newRow, 0).setCellValue(mobile);
					for (int j = 1; j < 10; j++) {
						StringBuffer twoNumber = new StringBuffer("");
					/*	if (j < 10) {
							twoNumber.append("0" + j);
						} else {
							twoNumber.append(j);
						}*/
						twoNumber.append(j);

						newRow = createRow(newSheet, newRowNumber);
						newRowNumber++;
						createCell(newRow, 0).setCellValue(nineNumber + twoNumber);
					}
					
				}
			}

			newWb.write(fileOutputStream);
			newWb.close();

		}

	}
	

	public static Sheet createSheet(Workbook wb, String sheetName) {
		Sheet sheet = wb.getSheet(sheetName);

		if (sheet == null) {
			sheet = wb.createSheet(sheetName);
		}

		return sheet;
	}

	// 创建行row
	public static Row createRow(Sheet sheet, int rowNum) {
		Row row = sheet.getRow(rowNum);

		if (row == null) {
			row = sheet.createRow(rowNum);
		}

		return row;
	}

	public static Cell createCell(Row row, int cellNum) {
		Cell cell = row.getCell(cellNum);

		if (cell == null) {
			cell = row.createCell(cellNum);
		}

		return cell;
	}

	// 获取单元格各类型值，返回字符串类型
	private static String getCellValueByCell(Cell cell) {
		// 判断是否为null或空串
		if (cell == null || cell.toString().trim().equals("")) {
			return "";
		}
		String cellValue = "";
		int cellType = cell.getCellType();

		switch (cellType) {
		case Cell.CELL_TYPE_STRING: // 字符串类型
			cellValue = cell.getStringCellValue().trim();
			cellValue = StringUtils.isEmpty(cellValue) ? "" : cellValue;
			break;
		case Cell.CELL_TYPE_BOOLEAN: // 布尔类型
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC: // 数值类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) { // 判断日期类型
				cellValue = date2String(cell.getDateCellValue(), "yyyy-MM-dd");
			} else { // 否
				cellValue = String.valueOf(new DecimalFormat("#.######").format(cell.getNumericCellValue()));
			}
			break;
		default: // 其它类型，取空串吧
			cellValue = "";
			break;
		}
		return cellValue;
	}

	public static boolean checkMobileNumber(String mobileNumber) {
		// String regex =
		// "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		String regex = "0?(13|14|15|16|17|18|19)[0-9]{9}";
		if (StringUtils.isBlank(mobileNumber) || mobileNumber.length() != 11) {
			return false;
		}

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mobileNumber);
		return m.matches();
	}

	public static String date2String(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static JSONObject getHttp(String path) {
		HttpURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		String result = null;// 返回结果字符串
		try {
			// 创建远程url连接对象
			URL url = new URL(path);
			// 通过远程url连接对象打开一个连接，强转成httpURLConnection类
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接方式：get
			connection.setRequestMethod("GET");
			// 设置连接主机服务器的超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取远程返回的数据时间：60000毫秒
			connection.setReadTimeout(60000);
			// 发送请求
			connection.connect();
			// 通过connection连接，获取输入流
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				// 封装输入流is，并指定字符集
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				// 存放数据
				StringBuffer sbf = new StringBuffer();
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
				System.out.println(result);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			connection.disconnect();// 关闭远程连接
		}

		return toJson(result);
	}

	public static JSONObject toJson(String result) {
		JSONObject jo = JSONObject.parseObject(result);
		return jo;
	}

}
