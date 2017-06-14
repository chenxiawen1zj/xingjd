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
public class Bossuser extends Model<Bossuser>{
	public static final Bossuser dao=new Bossuser();
	
	 //分页查询景点
	 @SuppressWarnings({"rawtypes" })
	public Page<Bossuser> paginate(int pageNumber, int pageSize,String sqlstr) throws UnsupportedEncodingException {
		 Page<Bossuser> page= paginate(pageNumber, pageSize, "select *",sqlstr);
			List<Bossuser> Bossuser=page.getList();
			int Scenicid=Bossuser.get(0).get("scenicId");
			String nickname=Bossuser.get(0).get("nickname");
			String denickname =java.net.URLDecoder.decode(nickname,"utf-8");
			String introduce=Bossuser.get(0).get("introduce");
			String deintroduce =java.net.URLDecoder.decode(introduce,"utf-8");
	 		Map map=new Bossuser().findnumbers(Scenicid);
	 		Bossuser.get(0).put("agentname", map.get("agentname"));
	 		Bossuser.get(0).put("denickname",denickname);
	 		Bossuser.get(0).put("deintroduce",deintroduce);
		 	
		 		
		 
		 return page;
	}
	 
	 //根据商家的id 找到这个商家所属的代理商，生成一个list
	 
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map findnumbers(int scenicId){
		 Map map=new HashMap();
		 int agentId=new Scenic().find("select agentId from t_scenic where scenicId=?",scenicId).get(0).getInt("agentId");
		 //所属代理商
		 String agentname=new Agent().find("select agentname from t_agent where agentId=? ",agentId).get(0).getAgentname();
		 map.put("agentname", agentname);
		 return map;
		 
	 }
	 
	 //更具物语用户id 找到这个用户然后拿出他的信息，把名字和介绍解码
	 public Bossuser findbybossuerid(int id) throws UnsupportedEncodingException {
		Bossuser bossuser=Bossuser.dao.findById(id);
		String nickname=bossuser.get("nickname");
		String denickname =java.net.URLDecoder.decode(nickname,"utf-8");
		String introduce=bossuser.get("introduce");
		String deintroduce =java.net.URLDecoder.decode(introduce,"utf-8");
		bossuser.put("denickname",denickname);
		bossuser.put("deintroduce",deintroduce);
		
		 return bossuser;
	}
	// 返回 0是存在 1是不存在 
	public int  findbossuser(Object id) {
		List<Bossuser> bossusers=new Bossuser().find("select * from t_bossuser where scenicId=?",id);
		if (bossusers.size()>0) {
			 return 0;
		}else {
			return 1;
		}

		
	}
}
