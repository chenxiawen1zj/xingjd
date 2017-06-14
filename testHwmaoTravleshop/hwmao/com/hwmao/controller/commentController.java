/**
 * 
 */
package com.hwmao.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.demo.common.model.Comment;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author Administrator
 *
 */
public class commentController extends Controller {
	
	public void index() {
		render("/hwmao/commentmgmt/comment.html");
	}
	
	public void  GetDepartment() throws UnsupportedEncodingException {
		Object scenicId=getSession().getAttribute("User");
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String search=getRequest().getParameter("search");	        
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        if(search==null){
        	sqlstr = "from t_comment c inner join t_photo p on c.photoId=p.photoId where p.scenicId="+scenicId+"  order by "+sort+" "+order;
        	
        }else{
        	sqlstr = "from t_comment where tel like '%"+search+"%' order by "+sort+" "+order;
        }
        Page<Comment> aEs =Comment.dao.paginate(pageNumber, pageSize,sqlstr);
        System.out.println(aEs);
        List<Comment> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}
	//禁用用户让state变成1
	public void forbidden(){
		int id=Integer.parseInt(getRequest().getParameter("id")); 
		Db.update("update t_comment set state=1 where commentId=? ",id);
		redirect("/comment");
	}
	public void recover() {
		int id=getParaToInt();
		Db.update("update t_comment set state=0 where commentId=? ",id);
		redirect("/comment");
	}

	
}
