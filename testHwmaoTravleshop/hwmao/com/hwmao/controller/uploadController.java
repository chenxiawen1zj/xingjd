package com.hwmao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.alibaba.fastjson.JSONObject;
import com.hwmao.util.CommonUtils;
import com.hwmao.util.Contants;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Ret;
import com.jfinal.upload.UploadFile;


/**
 * @author Administrator
 *
 */
public class uploadController extends Controller {
	//正式服务器的地址
	String serverPath = Contants.serverPath_server;
	String save=Contants.save_server;
	 //本地的测试地址
/*	String serverPath = Contants.serverPath_local;
	String save=Contants.save_local;*/
	//服务器的测试地址
/*	String serverPath = Contants.serverPath_servertest;
	String save=Contants.save_servertest;*/
	
	 public void imageUpload() {
		 UploadFile uploadFile = getFile(); 
		 String fileName = getPara("fileName");
		 fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 去掉路径
		// 异步上传时，无法通过File source = uploadFile.getFile();获取文件
		File source = new File(serverPath+fileName); // 获取临时文件对象
		System.out.println(source.exists());
		String extension = fileName.substring(fileName.lastIndexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String savePath = save + "/upload/scenicpic/"+ ymd;
		System.out.println("savePath是："+savePath);
		JSONObject json = new JSONObject();

		if (".png".equals(extension) || ".jpg".equals(extension)
				|| ".gif".equals(extension) || "jpeg".equals(extension)
				|| "bmp".equals(extension)) {
			fileName = CommonUtils.getCurrentTime() + extension;

			try {
				FileInputStream fis = new FileInputStream(source);
				File targetDir = new File(savePath);
				if (!targetDir.exists()) {
					targetDir.mkdirs();
				}

				File target = new File(targetDir, fileName);
				if (!target.exists()) {
					target.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(target);
				byte[] bts = new byte[1024 * 20];
				while (fis.read(bts, 0, 1024 * 20) != -1) {
					fos.write(bts, 0, 1024 * 20);
				}

				fos.close();
				fis.close();
				json.put("error", 0);
				json.put("src", "upload/scenicpic/" + ymd
						+ "/" + fileName); // 相对地址，显示图片用
				source.delete();

			} catch (FileNotFoundException e) {
				json.put("error", 1);
				json.put("message", "上传出现错误，请稍后再上传");
			} catch (IOException e) {
				json.put("error", 1);
				json.put("message", "文件写入服务器出现错误，请稍后再上传");
			}
		} else {
			source.delete();
			json.put("error", 1);
			json.put("message", "只允许上传png,jpg,jpeg,gif,bmp类型的图片文件");
		}
		System.out.println(json.toJSONString());
		renderJson(json.toJSONString());
	}
	 
	
	 public void imageUploadimg() {

		 UploadFile uploadFile = getFile(); 
		 String fileName = getPara("fileName");
		 fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 去掉路径
		// 异步上传时，无法通过File source = uploadFile.getFile();获取文件
		File source = new File(serverPath+fileName); // 获取临时文件对象
		System.out.println(source.exists());
		String extension = fileName.substring(fileName.lastIndexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String savePath = save + "/upload/images/"+ ymd;
		System.out.println("savePath是："+savePath);
		JSONObject json = new JSONObject();

		if (".png".equals(extension) || ".jpg".equals(extension)
				|| ".gif".equals(extension) || "jpeg".equals(extension)
				|| "bmp".equals(extension)) {
			fileName = CommonUtils.getCurrentTime() + extension;

			try {
				FileInputStream fis = new FileInputStream(source);
				File targetDir = new File(savePath);
				if (!targetDir.exists()) {
					targetDir.mkdirs();
				}

				File target = new File(targetDir, fileName);
				if (!target.exists()) {
					target.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(target);
				byte[] bts = new byte[1024 * 20];
				while (fis.read(bts, 0, 1024 * 20) != -1) {
					fos.write(bts, 0, 1024 * 20);
				}

				fos.close();
				fis.close();
				json.put("error", 0);
				json.put("src", "upload/images/" + ymd
						+ "/" + fileName); // 相对地址，显示图片用
				source.delete();

			} catch (FileNotFoundException e) {
				json.put("error", 1);
				json.put("message", "上传出现错误，请稍后再上传");
			} catch (IOException e) {
				json.put("error", 1);
				json.put("message", "文件写入服务器出现错误，请稍后再上传");
			}
		} else {
			source.delete();
			json.put("error", 1);
			json.put("message", "只允许上传png,jpg,jpeg,gif,bmp类型的图片文件");
		}
		System.out.println(json.toJSONString());
		renderJson(json.toJSONString());
	}
	 
	 public void imageUploadupdate() {

		 UploadFile uploadFile = getFile(); 
		 String fileName = getPara("fileName");
		 fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 去掉路径
		// 异步上传时，无法通过File source = uploadFile.getFile();获取文件
		File source = new File(serverPath+fileName); // 获取临时文件对象
		System.out.println(source.exists());
		String extension = fileName.substring(fileName.lastIndexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String savePath = save + "/upload/images/"+ ymd;
		System.out.println("savePath是："+savePath);
		JSONObject json = new JSONObject();

		if (".png".equals(extension) || ".jpg".equals(extension)
				|| ".gif".equals(extension) || "jpeg".equals(extension)
				|| "bmp".equals(extension)) {
			fileName = CommonUtils.getCurrentTime() + extension;

			try {
				FileInputStream fis = new FileInputStream(source);
				File targetDir = new File(savePath);
				if (!targetDir.exists()) {
					targetDir.mkdirs();
				}

				File target = new File(targetDir, fileName);
				if (!target.exists()) {
					target.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(target);
				byte[] bts = new byte[1024 * 20];
				while (fis.read(bts, 0, 1024 * 20) != -1) {
					fos.write(bts, 0, 1024 * 20);
				}

				fos.close();
				fis.close();
				json.put("error", 0);
				json.put("src", "upload/images/" + ymd
						+ "/" + fileName); // 相对地址，显示图片用
				source.delete();

			} catch (FileNotFoundException e) {
				json.put("error", 1);
				json.put("message", "上传出现错误，请稍后再上传");
			} catch (IOException e) {
				json.put("error", 1);
				json.put("message", "文件写入服务器出现错误，请稍后再上传");
			}
		} else {
			source.delete();
			json.put("error", 1);
			json.put("message", "只允许上传png,jpg,jpeg,gif,bmp类型的图片文件");
		}
		System.out.println(json.toJSONString());
		renderJson(json.toJSONString());
	}
	 //添加物语用户
	 public void imageUploadbossuser() {

		 UploadFile uploadFile = getFile(); 
		 String fileName = getPara("fileName");
		 fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 去掉路径
		// 异步上传时，无法通过File source = uploadFile.getFile();获取文件
		File source = new File(serverPath+fileName); // 获取临时文件对象
		System.out.println(source.exists());
		String extension = fileName.substring(fileName.lastIndexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String savePath = save + "/upload/bossuser/"+ ymd;
		System.out.println("savePath是："+savePath);
		JSONObject json = new JSONObject();

		if (".png".equals(extension) || ".jpg".equals(extension)
				|| ".gif".equals(extension) || "jpeg".equals(extension)
				|| "bmp".equals(extension)) {
			fileName = CommonUtils.getCurrentTime() + extension;

			try {
				FileInputStream fis = new FileInputStream(source);
				File targetDir = new File(savePath);
				if (!targetDir.exists()) {
					targetDir.mkdirs();
				}

				File target = new File(targetDir, fileName);
				if (!target.exists()) {
					target.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(target);
				byte[] bts = new byte[1024 * 20];
				while (fis.read(bts, 0, 1024 * 20) != -1) {
					fos.write(bts, 0, 1024 * 20);
				}

				fos.close();
				fis.close();
				json.put("error", 0);
				json.put("src", "upload/bossuser/" + ymd
						+ "/" + fileName); // 相对地址，显示图片用
				source.delete();

			} catch (FileNotFoundException e) {
				json.put("error", 1);
				json.put("message", "上传出现错误，请稍后再上传");
			} catch (IOException e) {
				json.put("error", 1);
				json.put("message", "文件写入服务器出现错误，请稍后再上传");
			}
		} else {
			source.delete();
			json.put("error", 1);
			json.put("message", "只允许上传png,jpg,jpeg,gif,bmp类型的图片文件");
		}

		renderJson(json.toJSONString());
	}
	 //添加物语用户内容
	 public void imageUploadbossmsg() {

		 UploadFile uploadFile = getFile(); 
		 String fileName = getPara("fileName");
		 fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 去掉路径
		// 异步上传时，无法通过File source = uploadFile.getFile();获取文件
		File source = new File(serverPath+fileName); // 获取临时文件对象
		System.out.println(source.exists());
		String extension = fileName.substring(fileName.lastIndexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String savePath = save + "/upload/bossmsg/"+ ymd;
		System.out.println("savePath是："+savePath);
		JSONObject json = new JSONObject();

		if (".png".equals(extension) || ".jpg".equals(extension)
				|| ".gif".equals(extension) || "jpeg".equals(extension)
				|| "bmp".equals(extension)) {
			fileName = CommonUtils.getCurrentTime() + extension;

			try {
				FileInputStream fis = new FileInputStream(source);
				File targetDir = new File(savePath);
				if (!targetDir.exists()) {
					targetDir.mkdirs();
				}

				File target = new File(targetDir, fileName);
				if (!target.exists()) {
					target.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(target);
				byte[] bts = new byte[1024 * 20];
				while (fis.read(bts, 0, 1024 * 20) != -1) {
					fos.write(bts, 0, 1024 * 20);
				}

				fos.close();
				fis.close();
				json.put("error", 0);
				json.put("src", "upload/bossmsg/" + ymd
						+ "/" + fileName); // 相对地址，显示图片用
				source.delete();

			} catch (FileNotFoundException e) {
				json.put("error", 1);
				json.put("message", "上传出现错误，请稍后再上传");
			} catch (IOException e) {
				json.put("error", 1);
				json.put("message", "文件写入服务器出现错误，请稍后再上传");
			}
		} else {
			source.delete();
			json.put("error", 1);
			json.put("message", "只允许上传png,jpg,jpeg,gif,bmp类型的图片文件");
		}

		renderJson(json.toJSONString());
	}
	 
	 //添加物语用户内容
	 @SuppressWarnings("unchecked")
	public void imageUploadtest() {
		 if ("config".equals(getPara("action"))) {

			// 这里千万注意 "config.json" 文件前方的目录一定要正确

			render("/ueditor/jsp/config.json");

			return;

			} 
		 UploadFile uploadFile = getFile("upfile"); 
		 String fileName = uploadFile.getFileName();
		 System.out.println(uploadFile);
		 fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 去掉路径
		// 异步上传时，无法通过File source = uploadFile.getFile();获取文件
		File source = new File(serverPath+fileName); // 获取临时文件对象
		System.out.println(source.exists());
		String extension = fileName.substring(fileName.lastIndexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String savePath = save + "/upload/test/"+ ymd;
		System.out.println("savePath是："+savePath);
		JSONObject json = new JSONObject();

		if (".png".equals(extension) || ".jpg".equals(extension)
				|| ".gif".equals(extension) || "jpeg".equals(extension)
				|| "bmp".equals(extension)) {
			fileName = CommonUtils.getCurrentTime() + extension;

			try {
				FileInputStream fis = new FileInputStream(source);
				File targetDir = new File(savePath);
				if (!targetDir.exists()) {
					targetDir.mkdirs();
				}

				File target = new File(targetDir, fileName);
				if (!target.exists()) {
					target.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(target);
				byte[] bts = new byte[1024 * 20];
				while (fis.read(bts, 0, 1024 * 20) != -1) {
					fos.write(bts, 0, 1024 * 20);
				}

				fos.close();
				fis.close();
				json.put("state","SUCCESS");
				json.put("title",fileName);
				json.put("original",uploadFile.getOriginalFileName());
				json.put("type","." + extension);
				json.put("size",uploadFile.getFile().length());
				json.put("url", "/upload/test/" + ymd
						+ "/" + fileName); // 相对地址，显示图片用
				source.delete();

			} catch (FileNotFoundException e) {
				json.put("error", 1);
				json.put("message", "上传出现错误，请稍后再上传");
			} catch (IOException e) {
				json.put("error", 1);
				json.put("message", "文件写入服务器出现错误，请稍后再上传");
			}
		} else {
			source.delete();
			json.put("error", 1);
			json.put("message", "只允许上传png,jpg,jpeg,gif,bmp类型的图片文件");
		}

		renderJson(json.toJSONString());
}
}