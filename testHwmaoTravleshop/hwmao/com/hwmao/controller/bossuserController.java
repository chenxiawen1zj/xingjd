/**
 * 
 */
package com.hwmao.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.demo.common.model.Agent;
import com.demo.common.model.Bossuser;
import com.demo.common.model.Scenic;
import com.hwmao.util.Contants;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author shop
 *
 */
public class bossuserController extends Controller {
	String save=Contants.savepath;
	
	public void  index() {
		render("/hwmao/bossuser/bossusertable.html");
	}
	
	public void  GetDepartment() throws UnsupportedEncodingException {
		Object scenicId=getSession().getAttribute("User");
		System.out.println(scenicId);
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String search=getRequest().getParameter("search");	        
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        if(search==null){
        	sqlstr = "from t_bossuser where `scenicId`="+scenicId+" order by "+sort+" "+order;
        	
        }else{
        	sqlstr = "from t_bossuser where tel like '%"+search+"%' order by "+sort+" "+order;
        }
        Page<Bossuser> aEs =Bossuser.dao.paginate(pageNumber, pageSize,sqlstr);
        List<Bossuser> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}
	//返回代理商的名字，商家的名字
	public void add(){
		Object scenicId=getSession().getAttribute("User");
		Scenic scenic=Scenic.dao.findById(scenicId);
		Integer agentId=scenic.getAgentId();
		String agentName=Agent.dao.findById(agentId).getAgentname();
		setAttr("scenic", scenic.put("agentName", agentName));
		System.out.println( scenic.put("agentName", agentName));
		render("/hwmao/bossuser/bossuseradd.html");
		
	}
	public void save() throws UnsupportedEncodingException {
		/*Object scenicId=getSession().getAttribute("User");*/
		Bossuser bossuser=getModel(Bossuser.class);
		String nickname=bossuser.get("nickname");
		String ennickname =java.net.URLEncoder.encode(nickname,"utf-8");
		String introduce=bossuser.get("introduce");
		String enintroduce =java.net.URLEncoder.encode(introduce,"utf-8");
		bossuser.set("nickname", ennickname).set("introduce", enintroduce).save();
		redirect("/bossuser");
	}
	
	//用户上传图片后没有完成，取消后，先删除图片
	public void cancle() {
		String picUrl=getPara("pictureSrc");
		String savePath = save+picUrl;
		File file = new File(savePath);	
		System.out.println(file);
		file.delete();
		renderJson(1);
	}
	public void detail() throws UnsupportedEncodingException {
		int id=Integer.parseInt(getRequest().getParameter("id")); 
		Bossuser bossuser=new Bossuser().findbybossuerid(id);
		setAttr("bossuser", bossuser);
		System.out.println(bossuser);
		render("/hwmao/bossuser/bossuseredit.html");
	}
	
	
	public void checkscenicboardremain(){
		Object scenicId=getSession().getAttribute("User");
		//返回 0是存在 1是不存在 
		int enough=new Bossuser().findbossuser(scenicId);
		if (enough==0) {
			//存在
			 renderJson(0);
		}else {
			//不存在
			 renderJson(1);	
		}
	}
	
	public void update() throws UnsupportedEncodingException {
		Bossuser bossuser=getModel(Bossuser.class);
		System.out.println(bossuser);
		//添加图片的路径
		String picUrladd=getPara("photoUrl");
		//原图片的路径
		String picUrl=getPara("bossuser.avatarUrl");
		if (picUrladd=="") {
			//不更新头像
			String nickname=bossuser.get("nickname");
			String ennickname =java.net.URLEncoder.encode(nickname,"utf-8");
			String introduce=bossuser.get("introduce");
			String enintroduce =java.net.URLEncoder.encode(introduce,"utf-8");
			bossuser.set("nickname", ennickname).set("introduce", enintroduce).update();
		}else {
			//图片上传，先删除源文件
			String savePath = save+picUrl;
			File file = new File(savePath);	
			System.out.println(file);
			file.delete();
			//更新头像
			String nickname=bossuser.get("nickname");
			String ennickname =java.net.URLEncoder.encode(nickname,"utf-8");
			String introduce=bossuser.get("introduce");
			String enintroduce =java.net.URLEncoder.encode(introduce,"utf-8");
			bossuser.set("nickname", ennickname).set("introduce", enintroduce).set("avatarUrl",picUrladd ).update();
		}

		
		redirect("/bossuser");
	}
}
