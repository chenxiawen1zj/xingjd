/**
 * 
 */
package com.hwmao.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.demo.common.model.Feedback;
import com.demo.common.model.Scenicboard;
import com.demo.common.model.User;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author Administrator
 *
 */
public class feedback extends Controller {
	public void index() {
		System.out.println("scenicBoardController被执行了");
		render("/hwmao/feedback/feedback.html");
	}
	
	public void  GetDepartment() throws UnsupportedEncodingException {
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String search=getRequest().getParameter("search");	        
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        if(search==null){
        	sqlstr = "from t_feedback order by "+sort+" "+order;
        	
        }else{
        	sqlstr = "from t_feedback where tel like '%"+search+"%' order by "+sort+" "+order;
        }
        Page<Feedback> aEs =Feedback.dao.paginate(pageNumber, pageSize,sqlstr);
        List<Feedback> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}
	public void add() {
		
	}
	
	public void update() {
		
	}
	
	public void del() {
		
	}
	
	public void save() {
		
	}
	
	public void edit() {
		renderText("1");
	}
	
	
	public void name() {
		
	}
	

	
	
}
