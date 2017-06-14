/**
 * 
 */
package com.demo.common.model;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.common.model.base.BaseScenicpic;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Scenicpic extends BaseScenicpic<Scenicpic> {
	
	public static final Scenicpic dao=new Scenicpic();
	 //分页查询用户
	 
	 
	 //分页查询景区的所有景点
	 @SuppressWarnings("static-access")
	public Page<Scenicpic> paginate(int pageNumber, int pageSize,String sqlstr) {
		 Page<Scenicpic> page=paginate(pageNumber, pageSize, "select *",sqlstr);
		 	List<Scenicpic> Scenicpic=page.getList();
		 	for(int i=0; i<Scenicpic.size(); i++){
		 		int as=Scenicpic.get(i).get("ctime");
				long ctime=new Long((long)as);
				String time=new com.hwmao.util.CommonUtils().getFirstTime(ctime);
				Scenicpic.get(i).put("time", time);
		 		int scenicPicId=Scenicpic.get(i).get("scenicPicId");
		 		Map<String, String> map=new Scenicpic().findnumbers(scenicPicId);
		 		Scenicpic.get(i).put("scenicname", map.get("scenicname"));
		 	}
		 	return page;
		}
	
	 
			//根据代理商的id 找到这个代理商的 商户数，应用数，留图数，留言数，生成一个list
			
			public Map<String, String> findnumbers(int scenicPicId){
				 Map<String, String> map=new HashMap<String, String>();
				 
				 String sql="select s.name from  t_scenicpic p inner join t_scenic s on p.scenicId=s.scenicId where p.scenicPicId =?";
				 List<Record> tittle =Db.find(sql,scenicPicId);
				 map.put("scenicname", tittle.get(0).getStr("name"));
				 return map;
				 
			}
			
			//更新景区图片
			public void updateScenicPic(String picUrl,int scenicId){
				Db.update("update t_scenicpic set picUrl=? where scenicId=?",picUrl,scenicId);
			}
}	
/*	 //分页查询某一景区的所有景点
	 (non-Javadoc)
	 * @see com.jfinal.plugin.activerecord.Model#paginate(int, int, java.lang.String, java.lang.String, java.lang.Object[])
	 
	public Page<Scenicpic> paginate(int pageNumber, int pageSize,int id) {
		// TODO Auto-generated method stub
		return paginate(pageNumber, pageSize, "select *", "from t_scenicpic where scennicId =?", id);
	}*/
	 

