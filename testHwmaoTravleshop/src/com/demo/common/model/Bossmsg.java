/**
 * 
 */
package com.demo.common.model;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Bossmsg extends Model<Bossmsg>{
	public static final Bossmsg dao=new Bossmsg();
	
	 //分页查询物语

	public Page<Bossmsg> paginate(int pageNumber, int pageSize,String sqlstr) throws UnsupportedEncodingException {
		 Page<Bossmsg> page= paginate(pageNumber, pageSize, "select *",sqlstr);
			List<Bossmsg> Bossmsg=page.getList();
			for(int i=0;i<Bossmsg.size();i++){
				String introduce=Bossmsg.get(i).get("introduce");
				int sceninBoardId=Bossmsg.get(i).get("sceninBoardId");
				Map map=findnumbers(sceninBoardId);
				if(introduce==null){
					
				}else {
					 introduce =java.net.URLDecoder.decode(introduce,"utf-8");
					
				}
				Bossmsg.get(i).put("tittle", map.get("tittle"));
				Bossmsg.get(i).put("introduce", introduce);
			}
	
			 return page;
	}
	
	 //根据商家的id 找到这个应用的名称，生成一个list
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map findnumbers(int sceninBoardId){
		 Map map=new HashMap();
		 String tittle=new Scenicboard().find("select tittle from t_scenicboard where sceninBoardId=?",sceninBoardId).get(0).get("tittle");
		 map.put("tittle", tittle);
		 return map;
		 
	 }
	 
	public List<Bossmsg> updateBossmsg(int id) throws UnsupportedEncodingException {
		List<Bossmsg> bossmsg=Bossmsg.dao.find("select * from t_bossmsg where id=?",id);
		String introduce=bossmsg.get(0).get("introduce");
		int sceninBoardId=bossmsg.get(0).get("sceninBoardId");
		String Scenicboardname=new Scenicboard().findById(sceninBoardId).get("tittle");
		bossmsg.get(0).put("Scenicboardname", Scenicboardname);
		if(introduce==null){
		}else {
			 String deintroduce =java.net.URLDecoder.decode(introduce,"utf-8");
			 bossmsg.get(0).put("deintroduce", deintroduce);
		}
		return bossmsg;
	}
	
}
