/**
 * 
 */
package com.hwmao.controller;

import com.demo.common.model.Agent;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author Administrator
 *
 */
public class agentController extends Controller {
	public void index() {
		render("/hwmao/agent/agenttable.html");
	}
	
	
	public void  GetDepartment() {
		Object agentId=getSession().getAttribute("User");
		
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String search=getRequest().getParameter("search");	        
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        if(search==null){
        	sqlstr = "from t_agent  where agentId="+agentId+" order by "+sort+" "+order;
        	
        }else{
        	sqlstr = "from t_agent where tel like '%"+search+"%' order by "+sort+" "+order;
        	
        }
        Page<Agent> aEs =Agent.dao.paginate(pageNumber, pageSize,sqlstr);
        java.util.List<Agent> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}



public void add() {
	
}

public void del() {
	
	int id=Integer.parseInt(getRequest().getParameter("id"));  
	System.out.println(id);
	Agent.dao.deleteById(id);
	redirect("/agent");

}

public void update() {
	getModel(Agent.class).update();
	System.out.println(getModel(Agent.class));
	redirect("/agent");
	
}

public void save() {
	getModel(Agent.class).save();
	redirect("/agent");
}

public void edit() {
	System.out.println(Agent.dao.findById(getParaToInt()));
	setAttr("agent", Agent.dao.findById(getParaToInt()));
	
	render("/hwmao/agent/agentedit.html");
}


public void findpic() {
	
	setAttr("agent", Agent.dao.findById(getParaToInt()));
	
	render("/hwmao/agent/agentedit.html");
}



}
