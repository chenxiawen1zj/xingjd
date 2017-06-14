/**
 * 
 */
package com.demo.common.model;

import java.io.UnsupportedEncodingException;
import java.util.List;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Guide extends Model<Guide> {
	
	public static final Guide dao=new Guide();
	
	
	public Page<Guide> paginate(int pageNumber, int pageSize,String sqlstr) throws UnsupportedEncodingException {
		 Page<Guide> page= paginate(pageNumber, pageSize, "select *",sqlstr);
			List<Guide> guides=page.getList();
			for(int i=0;i<guides.size();i++){
			int sceninBoardId=guides.get(i).get("sceninBoardId");
		 	String name=findnamebyid(sceninBoardId);
		 	guides.get(i).put("name", name);
			}	
		 
		 return page;
	}

	 //同过应用的id去找应用的名字
	public String findnamebyid(int sceninBoardId) {
		Scenicboard scenicboard=new Scenicboard().findById(sceninBoardId);
		String name=scenicboard.get("tittle");
		return name;
	}
	//返回应用的数据 
	public Guide findGuide(int id) {
		Guide guide=new Guide().findById(id);
		 int sceninBoardId=guide.get("sceninBoardId");
		 String name=findnamebyid(sceninBoardId);
		 guide.put("name", name); 
		 return guide;
	}
}
