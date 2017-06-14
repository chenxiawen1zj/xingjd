/**
 * 
 */
package com.hwmao.controller;

import java.util.List;

import com.demo.common.model.Agent;
import com.jfinal.core.Controller;

/**
 * @author Administrator
 *
 */
public class indexController extends Controller {
  
	public void getaccount() {
		System.out.println("indexController执行了");
		Object agentId=getSession().getAttribute("User");
		List<Agent> agents=new Agent().findAgentDetail(agentId);
		renderJson(agents);
	}
	
	
	
}
