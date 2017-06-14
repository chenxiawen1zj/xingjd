/**
 * 
 */
package com.hwmao.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import com.demo.common.model.Agent;
import com.demo.common.model.Photo;
import com.demo.common.model.Scenic;
import com.demo.common.model.Scenicpic;
import com.hwmao.util.Contants;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author shop
 *
 */
public class scenicController extends Controller {
	String save=Contants.savepath;
	public void index() {
		render("/hwmao/scenic/scenictable.html");
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
        	sqlstr = "from t_scenic where `scenicId`="+scenicId+" order by "+sort+" "+order;
        	
        }else{
        	sqlstr = "from t_scenic where name like '%"+search+"%' order by "+sort+" "+order;
        }
        Page<Scenic> aEs =Scenic.dao.paginate(pageNumber, pageSize,sqlstr);
        java.util.List<Scenic> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}
	public void add() {
		 Object agentId=getSession().getAttribute("User");
		 setAttr("agent", Agent.dao.findById(agentId));
		 render("/hwmao/scenic/scenicadd.html");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void update() {
		//添加图片的路径
		String picUrladd=getPara("pictureSrc");
		//原图片的路径

		String picUrl=getPara("scenic.picUrl");

		if (picUrladd=="") {
			//图片未上传
			getModel(Scenic.class).update();
		}else{
			//图片上传，先删除源文件
			
			String savePath = save+picUrl;
			File file = new File(savePath);	
			System.out.println(file);
			file.delete();
			// 然后保存 
			Integer scenicId=getParaToInt("scenic.scenicId");
			
			new Scenicpic().updateScenicPic(picUrladd, scenicId);;
		}
		System.out.println(picUrladd=="");
		redirect("/scenic");
	}
	
	//更新景区的封面的图片
	public void updateScenicPic() {
		 int id = getParaToInt();
		 List<Scenicpic> scenicpics=Scenicpic.dao. find("select * from t_scenicpic where scennicId=?",id);
		 String name=Scenic.dao.findById(id).getName();
		 if (scenicpics.size()>0) {
			
			 setAttr("scenicpic",scenicpics.get(0).put("name", name));
		}else {
			 setAttr("scenicpic",name);
		}
		
		 System.out.println(Scenicpic.dao.find("select * from t_scenicpic where scennicId=?",id));
		 render("/hwmao/scenic/scenicpicupdate.html");
		 
	}
	
	public void updateScenicinfo() {
		getModel(Scenic.class).update();
		redirect("/scenic");
	}
	//保存更新
	public void saveScenicPic() {
		//先删除原来的图片
		String picUrl=getPara("picUrl");
		String savePath = save+picUrl;
		File file = new File(savePath);	
		file.delete();
		//然后替换图片的地址
		String picupdate=getPara("photoUrl");
		System.out.println(picupdate);
		getModel(Scenicpic.class).set("picUrl",picupdate).update();
		redirect("/scenic");
	}
	public void save() {

		getModel(Scenic.class).save();
		redirect("/scenic");
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
	public void edit() {
		System.out.println("edit被执行了");
		Scenic scenic=Scenic.dao.findById(getParaToInt());
		int scenicId=scenic.getScenicId();
		System.out.println(scenicId);
		List<Scenicpic> scenicpic=Scenicpic.dao.find("select * from t_scenicpic where scenicId=?",scenicId);
		String picUrl=scenicpic.get(0).getPicUrl();
		scenic.put("picUrl", picUrl);
		
		setAttr("scenic", scenic);
		render("/hwmao/scenic/scenicedit.html");
	}
	public void checkloginName() {
		Object loginname=getRequest().getParameter("loginName");
		boolean check=new Scenic().checkloginName(loginname);
		renderJson(check);
	}
	

	public void detail() {
		 int id = Integer.parseInt(getRequest().getParameter("id"));
		 int agentId=Scenic.dao.findById(id).getInt("agentId");
		//商家的图片
		 String agentName=Agent.dao.findById(agentId).getAgentname();
		 String picUrl=Scenicpic.dao.find("select picUrl from t_scenicpic where scennicId=?",id).get(0).get("picUrl");
		 setAttr("scenic", Scenic.dao.findById(id).put("agentName", agentName).put("picUrl", picUrl));
		 System.out.println(Scenic.dao.findById(id).put("agentName", agentName).put("picUrl", picUrl));
		 render("/hwmao/scenic/scenicdetail.html");
	}
	
	public void getpicid() {
		 int id = getParaToInt();
		 setAttr("id", id);
		 render("/hwmao/scenic/scenicpictable.html");
	}
	
	public void getboardid() {
		 int id = getParaToInt();
		setAttr("id", id);
		render("/hwmao/scenic/scenicboardtable.html");
	}

	public void showpic() throws UnsupportedEncodingException {
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        int id = Integer.parseInt(getRequest().getParameter("id"));
        String sort=getRequest().getParameter("sort");
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        sqlstr = "from t_photo where scenicId = "+id+" order by "+sort+" "+order;
         Page<Photo> aEs =Photo.dao.paginate(pageNumber, pageSize,sqlstr);
        List<Photo> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}
	public void showboard(){
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        int id = Integer.parseInt(getRequest().getParameter("id"));
        String sort=getRequest().getParameter("sort");
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        sqlstr = "from t_scenicboard where scenicId = "+id+" order by "+sort+" "+order;
         Page<Scenic> aEs =Scenic.dao.paginate(pageNumber, pageSize,sqlstr);
        List<Scenic> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}
	//禁用用户让state变成1
	public void forbidden(){
		int id=Integer.parseInt(getRequest().getParameter("id")); 
		Db.update("update t_scenic set state=1 where scenicId=? ",id);
		redirect("/scenic");
	}
	public void recover() {
		int id=getParaToInt();
		Db.update("update t_scenic set state=0 where scenicId=? ",id);
		redirect("/scenic");
	}
}
