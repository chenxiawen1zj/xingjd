/**
 * 
 */
package com.hwmao.controller;


import com.demo.common.model.Scenicpic;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author Administrator
 *
 */
public class scenicPicController extends Controller {
	
	public void index() {
		 render("/hwmao/scenicpic/scenicpictable.html");
	}
	
	public void  GetDepartment() {
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String search=getRequest().getParameter("search");	        
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        if(search==null){
        	sqlstr = "from t_scenicpic order by "+sort+" "+order;
        	
        }else{
        	sqlstr = "from t_scenicpic where tel like '%"+search+"%' order by "+sort+" "+order;
        }
        Page<Scenicpic> aEs =Scenicpic.dao.paginate(pageNumber, pageSize,sqlstr);
        java.util.List<Scenicpic> a=aEs.getList();
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
