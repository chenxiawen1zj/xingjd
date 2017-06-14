package com.hwmao.util;


import com.demo.common.model.Agent;
import com.demo.common.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * BlogValidator.
 */
public class UserValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("user.username", "usernameMsg", "请输入用户名!");
		validateRequiredString("user.password", "passwordMsg", "请输入密码!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Agent.class);
		System.out.println(controller);
		String actionKey = getActionKey(); 
		if (actionKey.equals("hwmao/login"))
			controller.render("/hwmao/login.html");
	}
}
