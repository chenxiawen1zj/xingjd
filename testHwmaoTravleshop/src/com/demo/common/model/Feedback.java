/**
 * 
 */
package com.demo.common.model;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.common.model.base.BaseUser;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Feedback extends Model<Feedback> {
	
	public static final Feedback dao=new Feedback();
	

	
	 //分页查询用户
	 @SuppressWarnings({ "static-access", "rawtypes" })
	public Page<Feedback> paginate(int pageNumber, int pageSize,String sqlstr) throws UnsupportedEncodingException {
		 Page<Feedback> page=paginate(pageNumber, pageSize, "select *",sqlstr);
		 	List<Feedback> Feedback=page.getList();
		 	for(int i=0; i<Feedback.size(); i++){
		 		int as=Feedback.get(i).get("ctime");
				long ctime=new Long((long)as);
				String time=new com.hwmao.util.CommonUtils().gettime(ctime);
				Feedback.get(i).put("time", time);
				int userId=Feedback.get(i).getInt("userId");
				Map MAP=new Feedback().findnickcname(userId);
				String nickname=(String) MAP.get("nickname");
				String denickname =java.net.URLDecoder.decode(nickname,"utf-8");
				Feedback.get(i).put("denickname", denickname);
				Feedback.get(i).put("avatarUrl", MAP.get("avatarUrl"));

		 	}
		 	return page;
		}
	 

	//更具userId去找用户的昵称返回
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map findnickcname(int userId){
		 Map map=new HashMap();
		 Useradministrator list= new Useradministrator().findById(userId);
		 String avatarUrl=list.get("avatarUrl");
		 String nickname=list.get("nickname");
		 map.put("nickname", nickname);
		 map.put("avatarUrl", avatarUrl);
		 return map;
		 
	 }
	 
	 
}
