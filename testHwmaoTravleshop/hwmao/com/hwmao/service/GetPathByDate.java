/**
 * 
 */
package com.hwmao.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class GetPathByDate {
	/**
	 * 根据时间生成文件夹目录
	 * @return
	 */
	public static String getPath() {
		// 生成uid
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		String uid = retStrFormatNowDate + ".jpg";
		Calendar calendar = Calendar.getInstance();
		// 显示年份
		int year = calendar.get(Calendar.YEAR);
		// System.out.println("显示年份==>YEAR is = " + String.valueOf(year));
		// 显示月份 (从0开始, 实际显示要加一)
		int MONTH = calendar.get(Calendar.MONTH);
		int trueMONTH = MONTH + 1;
		// System.out.println("显示月份==>MONTH is = " + (MONTH + 1));

		File file1 = null;
		File file2 = null;
		String savePath = "UserImg";
		String outputFile1 = savePath + "\\" + year;
		String outputFile2 = savePath + "\\" + year + File.separator + trueMONTH;
		File picPath = new File(savePath);
		if (!picPath.exists()) {
			picPath.mkdirs();
		}
		file1 = new File(outputFile1);
		if (!file1.exists()) {
			file1.mkdir();
		}
		file2 = new File(outputFile2);
		if (!file2.exists()) {
			file2.mkdir();
		}
		return outputFile2;
	}
}
