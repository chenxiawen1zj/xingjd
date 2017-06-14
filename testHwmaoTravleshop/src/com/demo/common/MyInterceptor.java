package com.demo.common;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * 
 * 此拦截器仅做为示例展示，在本 demo 中并不需要
 */
public class MyInterceptor  implements Interceptor {
	
    @Override  
    
        // TODO Auto-generated method stub  
    	  public void intercept(Invocation inv) {
    	        Controller controller = inv.getController();
    	        
    	        String user =  String.valueOf(controller.getSessionAttr("User"));

    	        //判断登录条件是否成立(除了登录功能不拦截之外，其他都拦截)
    	        if(user == null && !inv.getMethod().getName().equals("login")){
    	            controller.render("/hwmao/login.html");
    	        }else{
    	            inv.invoke();
    	        }
    	        
    	    }

}
