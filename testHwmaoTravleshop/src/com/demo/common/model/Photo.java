/**
 * 
 */
package com.demo.common.model;

import java.awt.List;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.demo.common.model.base.BaseAgent;
import com.demo.common.model.base.BaseUser;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Photo extends Model<Photo> {
	
	public static final Photo dao=new Photo();
	//分页查询景点
	 @SuppressWarnings("static-access")
	public Page<Photo> paginate(int pageNumber, int pageSize,String sqlstr) throws UnsupportedEncodingException {
		 Page<Photo> page=paginate(pageNumber, pageSize, "select *",sqlstr);
		 	java.util.List<Photo> Photo=page.getList();
		 	for(int i=0; i<Photo.size(); i++){
		 		int as=Photo.get(i).get("ctime");
				long ctime=new Long((long)as);
				String time=new com.hwmao.util.CommonUtils().gettime(ctime);
				Photo.get(i).put("time", time);
				String nickname=Photo.get(i).get("nickname");
				String denickname =java.net.URLDecoder.decode(nickname,"utf-8");
				Photo.get(i).put("denickname", denickname);
				String introduce=Photo.get(i).get("introduce");
				if (introduce!=null) {
					String deintroduce =java.net.URLDecoder.decode(introduce,"utf-8");
					Photo.get(i).put("introduce", deintroduce);
				}
				
				
		 		int sceninBoardId=Photo.get(i).get("sceninBoardId");
		 		String sceninBoardname=new Photo().findscenicname(sceninBoardId);
		 		Photo.get(i).put("sceninBoardname", sceninBoardname);
		 	}
		 	return page;
		}
	 

	//更具scenicboardId去找应用的名字返回
	 public String findscenicname(int sceninBoardId){
		 Scenicboard list= new Scenicboard().findById(sceninBoardId);
		 String sceninBoardname=list.get("tittle");
		 return sceninBoardname;
		 
	 }
	 
}
