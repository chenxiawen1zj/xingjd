/**
 * 
 */
package com.demo.common.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.common.model.base.BaseAgent;
import com.demo.common.model.base.BaseUser;

import com.jfinal.plugin.activerecord.Page;


/**
 * @author 商户
 *
 */
@SuppressWarnings("serial")
public class Agent extends BaseAgent<Agent> {
	
	public static final Agent dao=new Agent();
	//分页查询 代理商页面内容
	 public Page<Agent> paginate(int pageNumber, int pageSize,String sqlstr) {
		 Page<Agent> page=paginate(pageNumber, pageSize, "select *",sqlstr);
		 	List<Agent> agents=page.getList();
		 	for(int i=0; i<agents.size(); i++){
/*		 		String  as=agents.get(i).get("ctime");
				long ctime=new Long((long)as);
				String time=new com.hwmao.util.CommonUtils().getFirstTime(ctime);
				agents.get(i).put("time", time);*/
		 		int agentid=agents.get(i).get("agentId");
		 		Map<String, Integer> map=new Agent().findnumbers(agentid);
		 		agents.get(i).put("scenicaccount", map.get("scenicaccount"));
		 		agents.get(i).put("scenicboardaccount", map.get("scenicboardaccount"));
		 		agents.get(i).put("picaccount", map.get("picaccount"));
		 	}
		 	return page;
		}
	 
	 
	 //根据代理商的id 找到这个代理商的 商户数，应用数，留图数，留言数，生成一个list
	 
	 public Map<String, Integer> findnumbers(int agentid){
		 Map<String, Integer> map=new HashMap<String, Integer>();
		 List<Scenic> scenics=new Scenic().find("select scenicId from t_scenic where agentId=?",agentid);
		 int scenicaccount=scenics.size();
		 int scenicboardaccount=0;
		 int picaccount=0;
		 for (int i=0;i<scenics.size();i++){
			 //遍历商户id找到所有的应用
			 int scenicId=scenics.get(i).getInt("scenicId");
			 int sizescenicboard=new Scenicboard().find("select sceninBoardId from t_scenicboard where scenicId=?", scenicId).size();
			 scenicboardaccount=scenicboardaccount+sizescenicboard;
			 int sizespic=new Photo().find("select photoId from t_photo where scenicId=?", scenicId).size();
			 picaccount=picaccount+sizespic;
			
		}
		 map.put("scenicaccount", scenicaccount);
		 map.put("scenicboardaccount", scenicboardaccount);
		 map.put("picaccount", picaccount);
		 
		 return map;
		 
	 }
		//查找用户是否存在
	 public List<Agent> findByNameAndPwd(String username, String password){  
	        return find("select * from t_agent where loginname='"+username+"' and password='"+password+"'");  
	    } 
	 //查找返回首页的数据
	 public List<Agent> findAgentDetail(Object agentId) {
		return find("select agentname,types,accountnumber,accountnumberremain from t_agent where agentId=?",agentId);
	}
	 
}
