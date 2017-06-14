/**
 * 
 */
package com.hwmao.controller;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.http.impl.cookie.PublicSuffixListParser;

import com.demo.common.model.Agent;
import com.demo.common.model.Bossmsg;
import com.demo.common.model.Fakeuser;
import com.demo.common.model.Photo;
import com.demo.common.model.Scenic;
import com.demo.common.model.Scenicboard;
import com.demo.common.model.Template;
import com.hwmao.util.Contants;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author 商家后台
 *
 */
public class photoController extends Controller {
	String save=Contants.savepath;
	
	//本地的存储地址
/*	String save=Contants.savepath_local;*/
	
	//服务器测试的存储地址
/*	String save=Contants.savepath_servertest;*/
	public void index() {
		
		render("/hwmao/picmgmt/picmgmt.html");
	}
	
	public void  GetDepartment() throws UnsupportedEncodingException {
		Object scenicId=getSession().getAttribute("User");
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String search=getRequest().getParameter("search");	
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        if(search==null){
        	sqlstr = "from t_photo where scenicId="+scenicId+" order by "+sort+" "+order;
        	
        }else{
        	
        	sqlstr = "from t_photo where introduce like '%"+search+"%' and scenicId="+scenicId+" order by "+sort+" "+order;
        }
        Page<Photo> aEs =Photo.dao.paginate(pageNumber, pageSize,sqlstr);
        java.util.List<Photo> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}
	//休闲版
	public void add() throws UnsupportedEncodingException {
		//景区(商家)id
		Object scenicId=getSession().getAttribute("User");
		Scenic scenics=new Scenic().findById(scenicId);
		setAttr("scenic", scenics);
		List<Agent> agents=new Agent().find("select a.agentname from t_agent a left join t_scenic s on a.agentId=s.agentId where s.scenicId=?",scenicId);
		String agentName=agents.get(0).getAgentname();
		setAttr("agent", agentName);
		List<Scenicboard> scenicboards =new Scenicboard().find("select * from t_scenicboard where scenicId=? and types=1",scenicId);
		List<Template> template =new Template().find("select * from t_template");
		List<Fakeuser> fakeusers=new Fakeuser().randomget();
		System.out.println(fakeusers);
		String nickname=fakeusers.get(0).getNickname();
		String denickname =java.net.URLDecoder.decode(nickname,"utf-8");
		setAttr("fakeuser", fakeusers.get(0).put("nickname", denickname));
		setAttr("scenicboard", scenicboards);
		setAttr("template", template);
		render("/hwmao/picmgmt/picadd.html");
		
	}
	
	//休闲版
		public void addtravel() throws UnsupportedEncodingException {
			//景区(商家)id
			Object scenicId=getSession().getAttribute("User");
			Scenic scenics=new Scenic().findById(scenicId);
			setAttr("scenic", scenics);
			List<Agent> agents=new Agent().find("select a.agentname from t_agent a left join t_scenic s on a.agentId=s.agentId where s.scenicId=?",scenicId);
			String agentName=agents.get(0).getAgentname();
			setAttr("agent", agentName);
			List<Scenicboard> scenicboards =new Scenicboard().find("select * from t_scenicboard where scenicId=? and types=0",scenicId);

			List<Fakeuser> fakeusers=new Fakeuser().randomget();
			System.out.println(fakeusers);
			String nickname=fakeusers.get(0).getNickname();
			String denickname =java.net.URLDecoder.decode(nickname,"utf-8");
			setAttr("fakeuser", fakeusers.get(0).put("nickname", denickname));
			setAttr("scenicboard", scenicboards);

			render("/hwmao/picmgmt/picaddtravel.html");
			
		}
	
	
	
	public void updatefake() throws UnsupportedEncodingException {
		int id=getParaToInt();
		String introduce=Photo.dao.findById(id).get("introduce");
		if (introduce!=null) {
			String deintroduce =java.net.URLDecoder.decode(introduce,"utf-8");
			setAttr("photo", Photo.dao.findById(id).set("introduce", deintroduce));
		}else {
			setAttr("photo", Photo.dao.findById(id));
		}
		
		System.out.println(Photo.dao.findById(id));
		render("/hwmao/picmgmt/picupdate.html");
	}
	
	public void del() {
		String picUrl=getPara("pictureSrc");
		String savePath = save+picUrl;
		File file = new File(savePath);	
		System.out.println(file);
		file.delete();
		
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
	@SuppressWarnings("static-access")
	public void save() throws UnsupportedEncodingException {
		String introduce=getModel(Photo.class). getStr("introduce");
		if (introduce!=null) {
			String enintroduce =java.net.URLEncoder.encode(introduce,"utf-8");
			long time=new com.hwmao.util.CommonUtils().getNowDateTime();
			getModel(Photo.class).set("introduce", enintroduce).set("ctime", time).save();
		}else {
			long time=new com.hwmao.util.CommonUtils().getNowDateTime();
			getModel(Photo.class).set("ctime", time).save();
		}
		redirect("/photo");
	}
	
	public void update() {
		//先删除原来的图片
		String picurl=getModel(Photo.class).get("photoUrl");
		String savePath = save+picurl;
		File file = new File(savePath);	
		file.delete();
		//然后替换图片的地址
		String picupdate=getPara("upUrl");
		System.out.println(picupdate);
		getModel(Photo.class).set("photoUrl",picupdate).update();
		redirect("/photo");
	}
	
/*	//保存更新
	public void savebossmsgPic() throws UnsupportedEncodingException {
		//先删除原来的图片
		Photo photo=getModel(Photo.class);
		int types=photo.getInt("types");
		//旅游版都要有
		if(types==0){
			
		}
		String photoUrl=getPara("upUrl");
		int length=photoUrl.length();
		System.out.println(photoUrl==null);
		String introduce=photo.get("introduce");
		if(introduce!=null && length==0) {
			System.out.println(1);
			introduce =java.net.URLEncoder.encode(introduce,"utf-8");
			photo.update();
		}else if (introduce==null&& length>0 ) {
			System.out.println(2);
			String picUrl=getPara("picUrl");
			String savePath = save+picUrl;
			File file = new File(savePath);	
			file.delete();
			//然后替换图片的地址
			photo.set("photoUrl",photoUrl).update();
		}else {
			System.out.println(3);
			introduce =java.net.URLEncoder.encode(introduce,"utf-8");
			String picUrl=getPara("picUrl");
			String savePath = save+picUrl;
			File file = new File(savePath);	
			file.delete();
			//然后替换图片的地址
			photo.set("photoUrl",photoUrl).update();
		}
		photo.update();
		redirect("/bossmsg");
	}*/
	public void edit() {
		renderText("1");
	}
	
	
	public void selection() {
		int id=getParaToInt();
		Db.update("update t_photo set selection=1 where photoId=? ",id);
		redirect("/photo");
	}
	
	public void whole() {
		int id=getParaToInt();
		Db.update("update t_photo set selection=0 where photoId=? ",id);
		redirect("/photo");
	}
	
	public void recover() {
		int id=getParaToInt();
		Db.update("update t_photo set state=0 where photoId=? ",id);
		redirect("/photo");
	}
	public void forbidden(){
		int id=Integer.parseInt(getRequest().getParameter("id")); 
		Db.update("update t_photo set state=1 where photoId=? ",id);
		redirect("/photo");
	}
	
	
}
