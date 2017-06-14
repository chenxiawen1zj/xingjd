/**
 * 
 */
package com.hwmao.controller;

import com.demo.common.model.Order;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author Administrator
 *
 */
public class orderController extends Controller {
	
	public void index() {
		render("/hwmao/order/alreadyPay.html");
	}
	
	public void  GetDepartment() {
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String search=getRequest().getParameter("search");	        
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        if(search==null){
        	sqlstr = "from t_order order by "+sort+" "+order;
        	
        }else{
        	sqlstr = "from t_order where orderId like '%"+search+"%' order by "+sort+" "+order;
        }
        Page<Order> aEs =Order.dao.paginate(pageNumber, pageSize,sqlstr);
        java.util.List<Order> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}
	public void  showpostcard() {
		 int id = Integer.parseInt(getRequest().getParameter("id"));
		 setAttr("id", id);
		 render("/hwmao/order/needMade.html");
	}
	
	public void  GetneedMade() {
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String search=getRequest().getParameter("search");	        
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        if(search==null){
        	sqlstr = "from t_order order by "+sort+" "+order;
        	
        }else{
        	sqlstr = "from t_order where orderId like '%"+search+"%' order by "+sort+" "+order;
        }
        Page<Order> aEs =Order.dao.paginate(pageNumber, pageSize,sqlstr);
        java.util.List<Order> a=aEs.getList();
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
