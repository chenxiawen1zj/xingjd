/**
 * 
 */
package com.hwmao.controller;


import java.io.UnsupportedEncodingException;
import java.util.List;

import com.demo.common.model.Agent;
import com.demo.common.model.Agentdrawmoneyrecord;
import com.demo.common.model.Agentmoenyrecord;
import com.demo.common.model.Scenic;
import com.demo.common.model.Scenicdrawmoneyrecord;
import com.demo.common.model.Scenicmoenyrecord;
import com.hwmao.util.SQLUtils;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;


/**
 * @author Administrator
 *
 */
public class moneyController extends Controller {
	
	public void Draw() {
		Object scenicId1= getSession().getAttribute("User");
		System.out.println(scenicId1);
		String sql2="select certificate from t_scenic where scenicId=?";
        List<Scenic> scenics=new Scenic().find(sql2, scenicId1);
       if (scenics.size()>0) {
			int certificate=scenics.get(0).get("certificate");
			
			setAttr("certificate", certificate);
		}
       render("/hwmao/money/drawmoney.html");
	}
	
	//返回提现页面的数据
	public void  GetDraw() throws UnsupportedEncodingException {
		Object agentId1= getSession().getAttribute("User");
		String agentId = String.valueOf(agentId1);
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String search=getRequest().getParameter("search");	        
        String order=getRequest().getParameter("order");
        SQLUtils sql = new SQLUtils(" from t_scenic where 1=1 ");

        if (agentId != null && agentId.length() != 0) {
        	
        	sql.whereEquals("`scenicId`", agentId);
    	}
        // 排序
        sql.append(" order by ").append(" "+sort).append(" "+order);
        System.out.println(sql.toString());
        Page<Scenic> aEs =Scenic.dao.paginate(pageNumber, pageSize,sql.toString());
        List<Scenic> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);

	}
	public void certificate() {
		int id=getParaToInt();
		setAttr("scenic", new Scenic().findById(id));
		System.out.println(new Scenic().findById(id));
		render("/hwmao/money/certificate.html");
	}
	
	public void bankcheck() {
		Scenic scenic=getModel(Scenic.class);
		//将认代理商的认证信息提交
		
		scenic.set("certificate", 2).update();
		System.out.println(scenic);
		render("/hwmao/money/drawmoney.html");
	}

	//处理体现请求
	public void drawcash(){
		 int id = Integer.parseInt(getRequest().getParameter("id"));  
	     String value=getRequest().getParameter("value");
	     System.out.println();
	    
	     if (value != null && value.length() != 0) {
	    	 double dd = Double.parseDouble(value)*100;	
	    	 Scenicdrawmoneyrecord scenicdrawmoneyrecord=new Scenicdrawmoneyrecord();
	    	 scenicdrawmoneyrecord.set("scenicId", id); 
	    	 scenicdrawmoneyrecord.set("money", dd); 
	    	 scenicdrawmoneyrecord.set("state", 0).save();
	    	 new Scenic().findById(id).set("drawmoney", 1).update();
	    	 renderJson(1);
	    	 
		}else {
			renderJson(2);
			return;
		}
	}
	
	
	//景点账目明细返回这个景点的得到的订单
		public void scenicorder() {
			Object scenicid1= getSession().getAttribute("User");
			String scenicid = String.valueOf(scenicid1);
			int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
			System.out.println(pageSize);
	        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
	        String sort=getRequest().getParameter("sort");
	        String search=getRequest().getParameter("search");	        
	        String order=getRequest().getParameter("order");
	        SQLUtils sql = new SQLUtils(" from t_scenicmoenyrecord a left join t_order o on a.oderid=o.id left join t_scenic s on a.scenicid=s.scenicId where 1=1");
	 
	        if (scenicid != null && scenicid.length() != 0) {
	        	
	        	sql.whereEquals(" a.scenicid ", scenicid);
	    	}
	        // 排序
	        sql.append(" order by ").append(" a."+sort).append(" "+order);
	        System.out.println(sql.toString());
	        Page<Scenicmoenyrecord> aEs =Scenicmoenyrecord.dao.paginate(pageNumber, pageSize,sql.toString());
	        List<Scenicmoenyrecord> a=aEs.getList();
	        String sellRecordJson=JsonKit.toJson(a);
	        int total=aEs.getTotalRow();
	        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
	        System.out.println(json);
	        renderJson(json);
		}
	//查询提现记录
	public void drawmoneyrecord() {
		Object scenicId1= getSession().getAttribute("User");
		String scenicId = String.valueOf(scenicId1);
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
		System.out.println(pageSize);
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String order=getRequest().getParameter("order");
        SQLUtils sql = new SQLUtils(" from t_scenicdrawmoneyrecord  where 1=1 and state=1");
 
        if (scenicId != null && scenicId.length() != 0) {
        	
        	sql.whereEquals("scenicId", scenicId);
    	}
        // 排序
        sql.append(" order by ").append(" "+sort).append(" "+order);
        System.out.println(sql.toString());
        Page<Scenicdrawmoneyrecord> aEs =Scenicdrawmoneyrecord.dao.paginate(pageNumber, pageSize,sql.toString());
        List<Scenicdrawmoneyrecord> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}
	
}
