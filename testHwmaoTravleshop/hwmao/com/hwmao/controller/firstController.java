/**
 * 
 */
package com.hwmao.controller;


import java.util.List;

import com.demo.common.model.Info;
import com.jfinal.core.Controller;


/**
 * @author Administrator
 *
 */
public class firstController extends Controller {
	public void index() {
		
		List<Info> infos=new Info().find("select messge from t_info where `show`=0 and `types`=2 ");
		System.out.println(infos);
		if (infos.size()>0) {
			setAttr("info", infos.get(0));
		}
		
		render("/hwmao/index_v2.html");
	}
	


	
	
}
