/**
 * 
 */
package com.demo.common.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.sql.visitor.functions.Length;
import com.demo.common.model.base.BaseAgent;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Scenicboard extends Model<Scenicboard> {
	
	public static final Scenicboard dao=new Scenicboard();
	 //分页查询用户
	 
	 
	 
	 @SuppressWarnings({ "static-access", "unchecked" })
	public Page<Scenicboard> paginate(int pageNumber, int pageSize,String sqlstr) {
		 Page<Scenicboard> page=paginate(pageNumber, pageSize, "select *",sqlstr);
		 		List<Scenicboard> Scenicboard=page.getList();
			 	for(int i=0; i<Scenicboard.size(); i++){
			 		int as=Scenicboard.get(i).get("ctime");
					long ctime=new Long((long)as);
					String time=new com.hwmao.util.CommonUtils().getFirstTime(ctime);
					Scenicboard.get(i).put("time", time);
			 		int sceninBoardId=Scenicboard.get(i).get("sceninBoardId");
			 		Map<String, Integer> map=new Scenicboard().findnumbers(sceninBoardId);
			 		Scenicboard.get(i).put("commontaccount", map.get("commontaccount"));
			 		Scenicboard.get(i).put("pv", map.get("pv"));
			 		Scenicboard.get(i).put("picaccount", map.get("picaccount"));
			 		Scenicboard.get(i).put("belongscenic", map.get("belongscenic"));
			 		Scenicboard.get(i).put("belongagent", map.get("belongagent"));
			 		Scenicboard.get(i).put("picurl", map.get("picurl"));
			 		Scenicboard.get(i).put("photo", map.get("photo"));
			 	}
			 	return page;
		}
	 
	 //查询某一景区的所有景点 图片
	 public List<Scenicboard> finAllBordByScenicId(int scenicId){
		 return find("select * from t_scenicboard where scenicId='"+scenicId+"");
	 }
	 
	 
	 //根据sceninBoardId  留图数，留言数，浏览量生成一个map 所属代理商 所属商家
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map findnumbers(int scenicBoardId){
		 Map map=new HashMap();
		 List<Scenicboard> scenics=new Scenicboard().find("select pv,scenicId from t_scenicboard where sceninBoardId=?",scenicBoardId);
		 List<Photo> photos=new Photo().find("select photoId from t_photo where sceninBoardId=?",scenicBoardId);
		 int scenicId=scenics.get(0).getInt("scenicId");
		 String sql="select s.name,a.agentname from t_scenic s inner join t_agent a on s.agentId=a.agentId where s.scenicId =?";
		 List<Record> tittle =Db.find(sql,scenicId);
		 String sql2="select picUrl,photo from t_wxaqrcode  where sceninBoardId =?";
		 List<Record> name =Db.find(sql2,scenicBoardId);
		 String sql3="select c.commentId  from t_comment c inner join t_photo p on c.photoId=p.photoId where p.sceninBoardId=?";
		 List<Record> commont =Db.find(sql3,scenicBoardId);
		 if (name.size()>0) {
			 for(int i=0;i<name.size();i++){
					String picurl=name.get(0).get("picUrl");
					String photo=name.get(0).get("photo");
					if (photo!=null && photo.length() !=0) {
						 map.put("photo", photo);
					}
					//二维码图片
					if (picurl!=null && picurl.length() !=0) {
						map.put("picurl", picurl);
					}
			}
		
		}
		 String belongscenic=tittle.get(0).getStr("name");
		 String belongagent=tittle.get(0).getStr("agentname");
		 int pv=scenics.get(0).getInt("pv");
		 int commontaccount=commont.size();
		 int picaccount=photos.size();
		 
		 map.put("pv", pv);
		 map.put("belongscenic", belongscenic);
		 map.put("belongagent", belongagent);
		 map.put("commontaccount", commontaccount);
		 map.put("picaccount", picaccount);
		 
		 return map;
		 
	 } 
	 
}
