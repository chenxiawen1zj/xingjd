/**
 * 
 */
package com.hwmao.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/** 
 * 将二进制流转换成图片文件 
 * @author lcw
 * 
 */  
public class ImgErToFileUtil {
    
   /** 
    * 将接收的字符串转换成图片保存 
    * @param imgStr 二进制流转换的字符串 
    * @param imgPath 图片的保存路径 
    * @param imgName 图片的名称 
    * @return  
    *      1：保存正常 
    *      0：保存失败 
    */  
   public static int saveToImgByStr(String imgStr,String imgPath,String imgName){  
try {  
   System.out.println("===imgStr.length()====>" + imgStr.length()  
           + "=====imgStr=====>" + imgStr);  
} catch (Exception e) {  
   e.printStackTrace();  
}  
       int stateInt = 1;  
       if(imgStr != null && imgStr.length() > 0){  
           try {  
                 
               // 将字符串转换成二进制，用于显示图片    
               // 将上面生成的图片格式字符串 imgStr，还原成图片显示    
               byte[] imgByte = hex2byte( imgStr );    
     
               InputStream in = new ByteArrayInputStream(imgByte);  
     
               File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等  
               FileOutputStream fos=new FileOutputStream(file);  
                   
               byte[] b = new byte[1024];  
               int nRead = 0;  
               while ((nRead = in.read(b)) != -1) {  
                   fos.write(b, 0, nRead);  
               }  
               fos.flush();  
               fos.close();  
               in.close();  
     
           } catch (Exception e) {  
               stateInt = 0;  
               e.printStackTrace();  
           } finally {  
           }  
       }  
       return stateInt;  
   }  
     
   /** 
    * 将二进制转换成图片保存 
    * @param imgStr 二进制流转换的字符串 
    * @param imgPath 图片的保存路径 
    * @param imgName 图片的名称 
    * @return  
    *      1：保存正常 
    *      0：保存失败 
    */  
   public static int saveToImgByBytes(File imgFile,String imgPath,String imgName){  
 
       int stateInt = 1;  
       if(imgFile.length() > 0){  
           try {  
               File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等  
               FileOutputStream fos=new FileOutputStream(file);  
                 
               FileInputStream fis = new FileInputStream(imgFile);  
                   
               byte[] b = new byte[1024];  
               int nRead = 0;  
               while ((nRead = fis.read(b)) != -1) {  
                   fos.write(b, 0, nRead);  
               }  
               fos.flush();  
               fos.close();  
               fis.close();  
     
           } catch (Exception e) {  
               stateInt = 0;  
               e.printStackTrace();  
           } finally {  
           }  
       }  
       return stateInt;  
   }  
 
   /** 
    * 二进制转字符串 
    * @param b 
    * @return 
    */  
   public static String byte2hex(byte[] b) // 二进制转字符串  
   {  
       StringBuffer sb = new StringBuffer();  
       String stmp = "";  
       for (int n = 0; n < b.length; n++) {  
           stmp = Integer.toHexString(b[n] & 0XFF);  
           if (stmp.length() == 1) {  
               sb.append("0" + stmp);  
           } else {  
               sb.append(stmp);  
           }  
 
       }  
       return sb.toString();  
   }  
 
   /** 
    * 字符串转二进制 
    * @param str 要转换的字符串 
    * @return  转换后的二进制数组 
    */  
   public static byte[] hex2byte(String str) { // 字符串转二进制  
       if (str == null)  
           return null;  
       str = str.trim();  
       int len = str.length();  
       if (len == 0 || len % 2 == 1)  
           return null;  
       byte[] b = new byte[len / 2];  
       try {  
           for (int i = 0; i < str.length(); i += 2) {  
               b[i / 2] = (byte) Integer  
                       .decode("0X" + str.substring(i, i + 2)).intValue();  
           }  
           return b;  
       } catch (Exception e) {  
           return null;  
       }  
   } 
  
   /**
    * 将二进制转换成文件保存
    * @param instreams 二进制流
    * @param imgPath 图片的保存路径
    * @param imgName 图片的名称
    * @return 
    *      1：保存正常
    *      0：保存失败
    */
   public static int saveToImgByInputStream(InputStream instreams,String imgPath,String imgName){
       int stateInt = 1;
       if(instreams != null){
           try {
        	   System.out.println(instreams.read());
               File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
               //创建输入流
               FileOutputStream fos=new FileOutputStream(file);
               System.out.println(fos);
               byte[] b = new byte[1024*20];
               int nRead = 0;
               while ((nRead = instreams.read(b)) != -1) {
                   fos.write(b, 0, nRead);
               }
               fos.flush();
               fos.close();                
           } catch (Exception e) {
               stateInt = 0;
               e.printStackTrace();
           } finally {
           }
       }
       System.out.println(stateInt);
       return stateInt;
      
   }
   
   public static int saveToImgByStr1(String imgStr,String imgPath,String imgName){
try {
   System.out.println("===imgStr.length()====>" + imgStr.length()
           + "=====imgStr=====>" + imgStr);
} catch (Exception e) {
   e.printStackTrace();
}
       int stateInt = 1;
       if(imgStr != null && imgStr.length() > 0){
           try {
                
               // 将字符串转换成二进制，用于显示图片  
               // 将上面生成的图片格式字符串 imgStr，还原成图片显示
        	  
               byte[] imgByte = hex2byte( imgStr );  
               System.out.println(imgByte.length);
               InputStream in = new ByteArrayInputStream(imgByte);
    
               File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
               FileOutputStream fos=new FileOutputStream(file);
                  
               byte[] b = new byte[1024];
               int nRead = 0;
               while ((nRead = in.read(b)) != -1) {
                   fos.write(b, 0, nRead);
               }
               fos.flush();
               fos.close();
               in.close();
    
           } catch (Exception e) {
               stateInt = 0;
               e.printStackTrace();
           } finally {
           }
       }
       return stateInt;
   }
   
   public static byte[] readInputStream(InputStream inStream) throws Exception{  
       ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
       //创建一个Buffer字符串  
       byte[] buffer = new byte[1024];  
       //每次读取的字符串长度，如果为-1，代表全部读取完毕  
       int len = 0;  
       //使用一个输入流从buffer里把数据读取出来  
       while( (len=inStream.read(buffer)) != -1 ){  
           //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
           outStream.write(buffer, 0, len);  
       }  
       //关闭输入流  
       inStream.close();  
       //把outStream里的数据写入内存  
       return outStream.toByteArray();  
   }  
     
}
