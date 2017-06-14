package com.hwmao.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import com.hwmao.util.HttpRequest;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.ehcache.CacheKit;
import net.sf.json.JSONObject;


public class CommonUtils {
	/** 默认的格式化方式 */
	private static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";

	public static String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		String formatCurrentDate = dateFormat.format(currentDate).toString();

		return formatCurrentDate;
	}

	public static String getCurrentDate() {
		String format = "yyyyMMdd";
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		if (format == null || "".equals(format.trim())) {
			format = defaultFormat;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String getCurrentTime() {
		String format = "yyyyMMddHHmmssSSS";
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		if (format == null || "".equals(format.trim())) {
			format = defaultFormat;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	 /**
     *  获得当前时间
     *  格式为：yyyy-MM-dd HH:mm:ss
    */
    public static String getNowTime() {
        Date nowday = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 精确到秒
        String time = sdf.format(nowday);
        return time;
    }

    /**
     * 获取当前系统时间戳
     * @return
     */
    public static long getNowTimeStamp() {
        return System.currentTimeMillis();
    }

    public static int getNowDateTime() {
        return (int) (new Date().getTime()/1000);
//        return new Date().getTime()/1000;
    }

    /**
     * 自定义日期格式
     * @param format
     * @return
     */
    public static String getNowTime(String format) {
        Date nowday = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);// 精确到秒
        String time = sdf.format(nowday);
        return time;
    }

    /**
     * 将时间字符转成Unix时间戳
     * @param timeStr
     * @return
     * @throws java.text.ParseException
     */
    public static Long getTime(String timeStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 精确到秒
        Date date = sdf.parse(timeStr);
        return date.getTime()/1000;
    }
    
    /**
     * 判断时间时候是今天的
     * @param timeStr
     * @return
     * @throws java.text.ParseException
     */
    @SuppressWarnings({ "static-access", "deprecation" })
	public  Boolean tiem(long timeStr) throws ParseException  {
    	 Calendar c1 = new GregorianCalendar();
		    c1.set(Calendar.HOUR_OF_DAY, 0);
		    c1.set(Calendar.MINUTE, 0);
		    c1.set(Calendar.SECOND, 0);
		   
		    long start=new CommonUtils().getTime(c1.getTime().toLocaleString());
		    Calendar c2 = new GregorianCalendar();
		    c2.set(Calendar.HOUR_OF_DAY, 23);
		    c2.set(Calendar.MINUTE, 59);
		    c2.set(Calendar.SECOND, 59);
		    long end=new CommonUtils().getTime(c2.getTime().toLocaleString());
		  
        
        return start<timeStr & timeStr<end;
    }
    /**
     * 将Unix时间戳转成时间字符
     * @param timestamp
     * @return
     */
    public static String getFirstTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 精确到日
        Date date = new Date(timestamp*1000);
        return sdf.format(date);
    }
    
    /**
     * 将Unix时间戳转成时间字符
     * @param timestamp
     * @return
     */
    public static String getCommontDetialTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("M月dd日HH:mm");// 精确到日
        Date date = new Date(timestamp*1000);
        return sdf.format(date);
    }
    /**
     * 将Unix时间戳转成时间字符
     * @param timestamp
     * @return
     */
    public static String getday(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");// 精确到日
        Date date = new Date(timestamp*1000);
        return sdf.format(date);
    }
    /**
     * 将Unix时间戳转成时间字符
     * @param timestamp
     * @return
     */
    public static String getmonth(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("M");// 精确到月
        Date date = new Date(timestamp*1000);
        return sdf.format(date);
    }
    //后台内容管理的发布时间用
    public static String gettime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 精确到月
        Date date = new Date(timestamp*1000);
        return sdf.format(date);
    }

    /**
     * 获取半年后的时间
     * 时间字符格式为：yyyy-MM-dd HH:mm:ss
     * @return 时间字符串
     */
    public static String getHalfYearLaterTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 精确到秒

        Calendar calendar = Calendar.getInstance();
        int currMonth = calendar.get(Calendar.MONTH) + 1;

        if (currMonth >= 1 && currMonth <= 6) {
            calendar.add(Calendar.MONTH, 6);
        } else {
            calendar.add(Calendar.YEAR, 1);
            calendar.set(Calendar.MONTH, currMonth - 6 - 1);
        }


        return sdf.format(calendar.getTime());
    }
    
    
    public static Map<String, String> sortMapByValue(Map<String, String> map) {  
        if (map == null || map.isEmpty()) {  
            return null;  
        }  
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();  
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(map.entrySet());  
        Collections.sort(entryList, new MapValueComparator());  
        Iterator<Map.Entry<String, String>> iter = entryList.iterator();  
        Entry<String, String> tmpEntry = null;  
        while (iter.hasNext()) {  
            tmpEntry = iter.next();  
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
        }  
        return sortedMap;  
    }
    //获取 access_token
    public String token() {
    	//小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wxbee0fe43f7e66afa";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "9aa7d7c6256614cc64e02ab143d60084";
        //授权（必填）
        String grant_type = "client_credential";
        String params = "grant_type=" + grant_type + "&appid=" + wxspAppid + "&secret=" + wxspSecret ;
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", params);
        JSONObject userInfoJSON = JSONObject.fromObject(sr);
        String a=(String) userInfoJSON.get("access_token");
        CacheKit.put("access_token", "access_token",a);
        System.out.println(a);
        return a;
	}
    
    //生成休闲版特殊二维码
  /*  * @return 
    *      1：保存正常
    *      0：保存失败*/
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public int createspecialcode(String save,String path,String access_token,int id) throws UnsupportedEncodingException {
 
     	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String savePath = save + "/upload/wxaqrcode/"+ ymd;
		String fileName = CommonUtils.getCurrentTime() + ".jpg";
		File targetDir = new File(savePath);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
		}
    	Map map=new HashMap();

    	map.put("path", path);
    	map.put("width", 430);
    	map.put("auto_color", false);
    	
    	int a;
    	
    	JSONObject jsonObject = JSONObject.fromObject(map);
    	String url="https://api.weixin.qq.com/wxa/getwxacode?access_token="+access_token;
        /*String params ="access_token="+access_token+ "&path=" + path + "&width=" + 430 ;*/
        //发送请求
    	String res=HttpKit.post(url,map.toString());
    	JSONObject json = JSONObject.fromObject(res);
    	String errcode = json.get("errcode").toString();
    	 int code = Integer.parseInt(errcode);
    	System.out.println(json);
    	System.out.println(jsonObject);
    	System.out.println(errcode=="42001");
    	if (code==42001){
    	  access_token=new CommonUtils().token();
    	  url="https://api.weixin.qq.com/wxa/getwxacode?access_token="+access_token;
    	  a = HttpRequest.sendJsonPost2(save,url, jsonObject,id);
    	  
    	 }else {
    	  a = HttpRequest.sendJsonPost2(save,url, jsonObject,id);
    	
		}
    	
    	return a ;
        /* //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();*/
	}
    //生成休闲版二维码
  /*  * @return 
    *      1：保存正常
    *      0：保存失败*/
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public int createwxaqrcode(String save,String path,String access_token,int id) throws UnsupportedEncodingException {
 
     	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String savePath = save + "/upload/wxaqrcode/"+ ymd;
		String fileName = CommonUtils.getCurrentTime() + ".jpg";
		File targetDir = new File(savePath);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
		}
    	Map map=new HashMap();

    	map.put("path", path);
    	map.put("width", 430);
    	
    	
    	int a;
    	
    	JSONObject jsonObject = JSONObject.fromObject(map);
    	String url="https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+access_token;
        /*String params ="access_token="+access_token+ "&path=" + path + "&width=" + 430 ;*/
        //发送请求
    	String res=HttpKit.post(url,map.toString());
    	JSONObject json = JSONObject.fromObject(res);
    	String errcode = json.get("errcode").toString();
    	 int code = Integer.parseInt(errcode);
    	System.out.println(json);
    	System.out.println(jsonObject);
    	System.out.println(errcode=="42001");
    	if (code==42001){
    	  access_token=new CommonUtils().token();
    	  url="https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+access_token;
    	  a = HttpRequest.sendJsonPost(save,url, jsonObject,id);
    	  
    	 }else {
    	  a = HttpRequest.sendJsonPost(save,url, jsonObject,id);
    	
		}
    	
    	return a ;

	}
    
    //更新休闲版二维码
  /*  * @return 
    *      1：保存正常
    *      0：保存失败*/
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public int updatewxaqrcode(String save,String path,String access_token,int id) throws UnsupportedEncodingException {
 
     	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String savePath = save + "/upload/wxaqrcode/"+ ymd;
		String fileName = CommonUtils.getCurrentTime() + ".jpg";
		File targetDir = new File(savePath);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
		}
    	Map map=new HashMap();

    	map.put("path", path);
    	map.put("width", 430);
    	
    	
    	int a;
    	
    	JSONObject jsonObject = JSONObject.fromObject(map);
    	String url="https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+access_token;
        /*String params ="access_token="+access_token+ "&path=" + path + "&width=" + 430 ;*/
        //发送请求
    	String res=HttpKit.post(url,map.toString());
    	JSONObject json = JSONObject.fromObject(res);
    	String errcode = json.get("errcode").toString();
    	 int code = Integer.parseInt(errcode);
    	System.out.println(json);
    	System.out.println(jsonObject);
    	System.out.println(errcode=="42001");
    	if (code==42001){
    	  access_token=new CommonUtils().token();
    	  url="https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+access_token;
    	  a = HttpRequest.sendJsonPost4(save,url, jsonObject,id);
    	  
    	 }else {
    	  a = HttpRequest.sendJsonPost4(save,url, jsonObject,id);
    	
		}
    	
    	return a ;

	}
    //更新休闲版特殊二维码
    /*  * @return 
      *      1：保存正常
      *      0：保存失败*/
      @SuppressWarnings({ "unchecked", "rawtypes" })
  	public int updatespecialcode(String save,String path,String access_token,int id) throws UnsupportedEncodingException {
   
       	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
  		String ymd = sdf.format(new Date());
  		String savePath = save + "/upload/wxaqrcode/"+ ymd;
  		String fileName = CommonUtils.getCurrentTime() + ".jpg";
  		File targetDir = new File(savePath);
  			if (!targetDir.exists()) {
  				targetDir.mkdirs();
  		}
      	Map map=new HashMap();

      	map.put("path", path);
      	map.put("width", 430);
      	map.put("auto_color", false);
      	
      	int a;
      	
      	JSONObject jsonObject = JSONObject.fromObject(map);
      	String url="https://api.weixin.qq.com/wxa/getwxacode?access_token="+access_token;
          /*String params ="access_token="+access_token+ "&path=" + path + "&width=" + 430 ;*/
          //发送请求
      	String res=HttpKit.post(url,map.toString());
      	JSONObject json = JSONObject.fromObject(res);
      	String errcode = json.get("errcode").toString();
      	 int code = Integer.parseInt(errcode);
     
      	if (code==42001){
      	  access_token=new CommonUtils().token();
      	  url="https://api.weixin.qq.com/wxa/getwxacode?access_token="+access_token;
      	  a = HttpRequest.sendJsonPost3(save,url, jsonObject,id);
      	  
      	 }else {
      	  a = HttpRequest.sendJsonPost3(save,url, jsonObject,id);
      	
  		}
      	
      	return a ;
          /* //解析相应内容（转换成json对象）
          JSONObject json = JSONObject.fromObject(sr);
          //获取会话密钥（session_key）
          String session_key = json.get("session_key").toString();*/
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
                File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos=new FileOutputStream(file);
                byte[] b = new byte[1024];
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
        return stateInt;
    }
}     


