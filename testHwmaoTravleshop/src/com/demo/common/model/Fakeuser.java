/**
 * 
 */
package com.demo.common.model;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.common.model.base.BaseFakeuser;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Fakeuser extends BaseFakeuser<Fakeuser> {
	
	public static final Fakeuser dao=new Fakeuser();
	
	 //分页查询用户
	 @SuppressWarnings({"rawtypes" })
	public Page<Fakeuser> paginate(int pageNumber, int pageSize,String sqlstr) throws UnsupportedEncodingException {
		 Page<Fakeuser> page= paginate(pageNumber, pageSize, "select *",sqlstr);
			List<Fakeuser> Fakeuser=page.getList();
		 	for(int i=0; i<Fakeuser.size(); i++){
		 		String nickname=Fakeuser.get(i).get("nickname");
	            String decontent =java.net.URLDecoder.decode(nickname,"utf-8");
		 		int Fakeuserid=Fakeuser.get(i).getId();
		 		Map map=new Fakeuser().findnumbers(Fakeuserid);
		 		Fakeuser.get(i).put("nickname",decontent);
		 		Fakeuser.get(i).put("picaccount", map.get("picaccount"));
		 		Fakeuser.get(i).put("pvaccount", map.get("pvaccount"));
		 	}
		 return page;
	  }
	 
	 //根据虚拟用户的id 找到这个用户的留言数，和留图数
	 
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map findnumbers(int Id){
		 Map map=new HashMap();

		 List<Photo> photos=new Photo().find("select photoId from t_photo where fakeid=?",Id);
		 //留图数
		 System.out.println(photos);
		 int picaccount=photos.size();
		//留言数没有写
		 int pvaccount=0;
		 map.put("picaccount", picaccount);
		 map.put("pvaccount", pvaccount);

		 return map;
		 
	 }
		//虚拟用户头像
		public void updateFakePic(String avatarUrl,int id){
			Db.update("update t_fakeuser set avatarUrl=? where id=?",avatarUrl,id);
		}
		//随机读取一条记录
		public List<Fakeuser> randomget(){
			String sql="select * from t_fakeuser order by rand( ) limit 1;";
			return new Fakeuser().find(sql);
		}
		
}
