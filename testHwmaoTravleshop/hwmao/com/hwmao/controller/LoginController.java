/**
 * 
 */
package com.hwmao.controller;


import java.util.List;


import com.common.controller.BaseController;
import com.demo.common.model.Backstagelogin;
import com.demo.common.model.Scenic;
import com.jfinal.plugin.ehcache.CacheKit;




/**
 * @author Administrator
 *
 */
public class LoginController extends BaseController {
	
	public void index() {
		render("/hwmao/login.html");
	}
/*	@Before(UserValidator.class)*/
	public void login() {
		Scenic scenic=getModel(Scenic.class);
		List<Scenic> scenic1 =Scenic.dao.findByNameAndPwd(scenic.getLoginname(), scenic.getPassword());
	
		if (scenic1.size()>0) {
			System.out.println(scenic1.get(0).getStr("name"));
			setAttr("name",scenic1.get(0).getName());
			int scenicId=scenic1.get(0).getScenicId();
			System.out.println(scenicId);
			CacheKit.put("scenicId", scenicId, scenicId);
			getSession().setAttribute("User", scenicId);
			new Backstagelogin().updatelogin(scenicId);
			System.out.println("上面");
			render("/hwmao/main.html");
		}else {
			System.out.println("下面");
			setAttr("erro", "账号或密码错误");
			render("/hwmao/login.html");
			
		}
		
	
	}
}
