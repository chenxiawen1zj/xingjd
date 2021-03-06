package com.demo.common.model;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.common.model.base.BaseComment;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Comment extends BaseComment<Comment> {
	public static final Comment dao = new Comment().dao();
	//分页查询评论
		 @SuppressWarnings("static-access")
		public Page<Comment> paginate(int pageNumber, int pageSize,String sqlstr) throws UnsupportedEncodingException {
			 Page<Comment> page=paginate(pageNumber, pageSize, "select c.commentId,c.state,c.content,c.from_uid,c.to_uid,c.ctime",sqlstr);
			 	List<Comment> Comment=page.getList();
			 	for(int i=0; i<Comment.size(); i++){
			 		//时间转码
			 		int as=Comment.get(i).get("ctime");
					long ctime=new Long((long)as);
					String time=new com.hwmao.util.CommonUtils().gettime(ctime);
					Comment.get(i).put("time", time);
					//昵称获取
					int from_uid=Comment.get(i).getInt("from_uid");
					int to_uid=Comment.get(i).getInt("to_uid");
					//评论解码
					String content=Comment.get(i).get("content");
					String decontent =java.net.URLDecoder.decode(content,"utf-8");
					Comment.get(i).put("decontent", decontent);
					
					Map map=findnumbers(from_uid, to_uid);
					String nickname=(String) map.get("fromNickname");
					String toNickname=(String) map.get("toNickname");
					
					if (toNickname==null) {
						Comment.get(i).put("toNickname", toNickname);
					}else {
						String detoNickname =java.net.URLDecoder.decode(toNickname,"utf-8");
						Comment.get(i).put("toNickname", detoNickname);
					}
					String denickname =java.net.URLDecoder.decode(nickname,"utf-8");
					Comment.get(i).put("nickname", denickname);
			 		
			 	}
			 	return page;
			}
	 //根据from_uid  to_uid 找到这个id对应的nickname
		  @SuppressWarnings({ "unchecked", "rawtypes" })
		public Map findnumbers(int from_uid ,int to_uid){
			 Map map=new HashMap();
			 String fromNickname=new Useradministrator().find("select nickname from t_user where id=?",from_uid).get(0).get("nickname");
			 //所属代理商
			 String toNickname;
			 if(to_uid==0){
				toNickname=null;
			 }else {
				toNickname=new Useradministrator().find("select nickname from t_user where id=?",to_uid).get(0).get("nickname");
			}
			 map.put("fromNickname", fromNickname);
			 map.put("toNickname", toNickname);
			 return map;
			 
		 }
}
