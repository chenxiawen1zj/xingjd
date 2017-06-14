/**
 * 
 */
package com.hwmao.controller;



import java.io.UnsupportedEncodingException;
import java.security.Timestamp;
import java.util.List;
import com.demo.common.model.User;
import com.hwmao.dao.administrator;
import com.jfinal.core.Controller;

import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author Administrator
 *
 */
public class tableController extends Controller{
	public void index() {
		render("/hwmao/administrator/table.html");
		
	}
	@SuppressWarnings("static-access")
	public void  GetDepartment() throws UnsupportedEncodingException {
			int pageSize = Integer.parseInt(getRequest().getParameter("limit"));
			int pageNumber = Integer.parseInt(getRequest().getParameter("offset"))+1;
	        String sort=getRequest().getParameter("sort");
	        String search=getRequest().getParameter("search");	        
	        String order=getRequest().getParameter("order");
	 /*       String sqlstr ="";
	        if(search==null){
	        	sqlstr = "from user order by "+sort+" "+order;
	        	
	        }else{
	        	sqlstr = "from user where tel like '%"+search+"%' order by "+sort+" "+order;
	        }*/
	        Page<administrator> aEs =administrator.dao.findfirstpagedate(pageNumber, pageSize,sort,order);

	        for(int  i=0;  i<aEs.getList().size(); i++){
				int as=aEs.getList().get(i).get("ctime");
		        long ctime=new Long((long)as);
				String time=new com.hwmao.util.CommonUtils().getFirstTime(ctime);
				aEs.getList().get(i).put("time", time);
				
			}
	        List<administrator> a=aEs.getList();
	        String sellRecordJson=JsonKit.toJson(a);
	        int total=aEs.getTotalRow();
	        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
	        System.out.println(json);
	        renderJson(json);
		}
	
	
	
	public void add() {
		
	}
	//禁用用户让state变成1
	public void forbidden(){
		int id=Integer.parseInt(getRequest().getParameter("id")); 
		Db.update("update t_administrator set state=1 where id=? ",id);
		renderJson("ok");
	}
	public void del() {
		/*Integer.parseInt(getRequest().getParameter("id"));  */
		int id=Integer.parseInt(getRequest().getParameter("id"));  
		System.out.println(id);
		User.dao.deleteById(id);
		redirect("/table");
	
	}
	
	public void update() {
		getModel(User.class).update();
		System.out.println(getModel(User.class));
		redirect("/table");
		
	}
	
	public void save() {
		getModel(User.class).save();
		redirect("/table");
	}
	
	
	public void reset() {
		setAttr("administrator", administrator.dao.findById(getParaToInt()));
		
		render("/hwmao/administrator/reset.html");
	}
	
	public void edit() {
		setAttr("administrator", administrator.dao.findById(getParaToInt()));
	
		render("/hwmao/administrator/edit.html");
	}
}
