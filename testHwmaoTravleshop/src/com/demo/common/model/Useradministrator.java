/**
 * 
 */
package com.demo.common.model;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.demo.common.model.base.BaseAdministrator;
import com.demo.common.model.base.BaseUser;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Useradministrator extends BaseAdministrator<Useradministrator> {
	
	public static final Useradministrator dao=new Useradministrator();
	

	
	 //分页查询用户
	 @SuppressWarnings("static-access")
	public Page<Useradministrator> paginate(int pageNumber, int pageSize,String sqlstr) throws UnsupportedEncodingException {
		 Page<Useradministrator> page=paginate(pageNumber, pageSize, "select *",sqlstr);
		 	List<Useradministrator> User=page.getList();
		 	for(int i=0; i<User.size(); i++){
		 		int as=User.get(i).get("ctime");
				long ctime=new Long((long)as);
				String time=new com.hwmao.util.CommonUtils().gettime(ctime);
				User.get(i).put("time", time);
				String nickname=User.get(i).get("nickname");
				String denickname =java.net.URLDecoder.decode(nickname,"utf-8");
				User.get(i).put("denickname", denickname);

		 	}
		 	return page;
		}
	 

	//更具scenicboardId去找应用的名字返回
	 public String findscenicname(int scenicBoardId){
		 Scenicboard list= new Scenicboard().findById(scenicBoardId);
		 String sceninBoardname=list.get("tittle");
		 return sceninBoardname;
		 
	 }
	 
	 
}
