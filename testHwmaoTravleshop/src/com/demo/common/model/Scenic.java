/**
 * 
 */
package com.demo.common.model;




import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.demo.common.model.base.BaseScenic;
import com.jfinal.plugin.activerecord.Page;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Scenic extends BaseScenic<Scenic> {
	
	public static final Scenic dao=new Scenic();
	//分页查询景点
	 @SuppressWarnings({  "rawtypes" })
	public Page<Scenic> paginate(int pageNumber, int pageSize,String sqlstr) {
		 Page<Scenic> page= paginate(pageNumber, pageSize, "select *",sqlstr);
			List<Scenic> Scenic=page.getList();
		 	for(int i=0; i<Scenic.size(); i++){
		 		int Scenicid=Scenic.get(i).get("scenicId");
		 		int as=Scenic.get(i).get("ctime");
				long ctime=new Long((long)as);
				String time=new com.hwmao.util.CommonUtils().getFirstTime(ctime);
				Scenic.get(i).put("time", time);
		 		Map map=new Scenic().findnumbers(Scenicid);
		 		List<Scenicpic> scenicpics=new Scenicpic().find("select picUrl from t_scenicpic where scennicId=?",Scenicid);
		 		/*String picUrl=new Scenicpic().find("select picUrl from t_scenicpic where scennicId=?",Scenicid).get(0).getStr("picUrl");*/
		 		System.out.println(scenicpics.size());
		 		if (scenicpics.size()>0) {
		 			String picUrl=scenicpics.get(0).getStr("picUrl");
		 			Scenic.get(i).put("picUrl",picUrl);
				}else {
					
				}
		 		Scenic.get(i).put("agentname", map.get("agentname"));
		 		Scenic.get(i).put("scenicboardaccount", map.get("scenicboardaccount"));
		 		Scenic.get(i).put("picaccount", map.get("picaccount"));
		 		Scenic.get(i).put("pvaccount", map.get("pvaccount"));
		 		Scenic.get(i).put("bossmsgaccount", map.get("bossmsgaccount"));
		 	}
		 return page;
	 
	 
	 
	 
	 
	 }
	 
	 //根据商家的id 找到这个商家所属的代理商，应用数，留图数，商家的总流量，物语数，生成一个list
	 
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map findnumbers(int scenicId){
		 Map map=new HashMap();
		 int agentId=new Scenic().find("select agentId from t_scenic where scenicId=?",scenicId).get(0).getInt("agentId");
		 //所属代理商
		 String agentname=new Agent().find("select agentname from t_agent where agentId=? ",agentId).get(0).getAgentname();
		 //应用数
		 List<Scenicboard> scenicboards=new Scenicboard().find("select sceninBoardId from t_scenicboard where scenicId=?",scenicId);
		 int scenicboardaccount=scenicboards.size();
		 
		 //留图数
		 int pvaccount=0;
		 int picaccount=0;
		 for (int i=0;i<scenicboards.size();i++){
			 //遍历商户id找到所有 sceninBoardId
			 int sceninBoardId=scenicboards.get(i).getInt("sceninBoardId");
			 //每一块牌子的流量
			 int pv=new Scenicboard().find("select pv from t_scenicboard where sceninBoardId=?", sceninBoardId).get(0).getInt("pv");
			 pvaccount=pvaccount+pv;
			 int sizespic=new Photo().find("select photoId from t_photo where sceninBoardId=?", sceninBoardId).size();
			 picaccount=picaccount+sizespic;
			
		}
		 //物语数
		 List<Bossmsg> bossmsgs=new Bossmsg().find("select * from t_bossmsg where scenicId=?",scenicId);
		 int  bossmsgaccount=bossmsgs.size();
		 map.put("bossmsgaccount", bossmsgaccount);
		 map.put("agentname", agentname);
		 map.put("scenicboardaccount", scenicboardaccount);
		 map.put("picaccount", picaccount);
		 map.put("pvaccount", pvaccount);
		 
		 return map;
		 
	 }
		//查找用户是否存在
	 public List<Scenic> findByNameAndPwd(String username, String password){  
	        return find("select * from t_scenic where loginname='"+username+"' and password='"+password+"'");  
	    } 
	 
	//检查这个用户名是否被注册 true被注册 false 没有被注册
	public boolean checkloginName(Object loginName) {
		List<Scenic> list=Scenic.dao.find("select scenicId from t_scenic where loginname=?", loginName);
		System.out.println(list);
		boolean check=list.size()>0;
		System.out.println(check);
		return check;
	}
	//1 足够  0不足够
	public int findscenicboardremain(Object scenicId) {
		List<Scenic> list=Scenic.dao.find("select scenicboardremain from t_scenic where scenicId=?", scenicId);
		int Scenicboardremain=list.get(0).getScenicboardremain();
		System.out.println("Scenicboardremain"+Scenicboardremain);
		if (Scenicboardremain>0) {
			return 1;
		}else {
			return 0;
		}
	}
}
