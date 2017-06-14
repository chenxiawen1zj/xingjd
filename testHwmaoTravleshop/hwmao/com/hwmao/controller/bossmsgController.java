/**
 * 
 */
package com.hwmao.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.demo.common.model.Agent;
import com.demo.common.model.Bossmsg;
import com.demo.common.model.Bossuser;
import com.demo.common.model.Scenic;
import com.demo.common.model.Scenicboard;
import com.demo.common.model.Scenicpic;
import com.hwmao.util.Contants;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author shop 物语管理
 *
 */
public class bossmsgController extends Controller {
	String save=Contants.savepath;
	

	
	public void  index() {
		render("/hwmao/bossmsg/bossmsgtable.html");
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
        	/*`scenicId`="+scenicId+"*/
        	sqlstr = "from t_bossmsg where scenicId="+scenicId+" order by "+sort+" "+order;
        	System.out.println(sqlstr);
        	
        }else{
        	sqlstr = "from t_bossmsg where tel like '%"+search+"%' order by "+sort+" "+order;
        }
        Page<Bossmsg> aEs =Bossmsg.dao.paginate(pageNumber, pageSize,sqlstr);
        List<Bossmsg> a=aEs.getList();
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
		List<Scenicboard> scenicboards =new Scenicboard().find("select * from t_scenicboard where scenicId=? and types=1 ",scenicId);
		System.out.println();
		setAttr("scenicboard", scenicboards);
		render("/hwmao/bossmsg/bossmsgadd.html");
		
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
	
	
	public void  update() {
		Object scenicId=getSession().getAttribute("User");
	}
	
	
	//更新物语图片和内容
	public void updatebossmsgpic() throws UnsupportedEncodingException {
		 int id = getParaToInt();
		 List<Bossmsg> bossmsg=new Bossmsg().updateBossmsg(id);
		 setAttr("bossmsg",bossmsg.get(0));
		 System.out.println(bossmsg.get(0));
		 render("/hwmao/bossmsg/bossmsgupdate.html");
		 
	}
	
	//保存更新
	public void savebossmsgPic() throws UnsupportedEncodingException {
		//先删除原来的图片
		Bossmsg bossmsg=getModel(Bossmsg.class);
		int id=bossmsg.getInt("id");
		String photoUrl=getPara("photoUrl");
		int length=photoUrl.length();
		System.out.println(photoUrl==null);
		String introduce=bossmsg.get("introduce");
		if(introduce!=null && length==0) {
			System.out.println(1);
			introduce =java.net.URLEncoder.encode(introduce,"utf-8");
			bossmsg.update();
		}else if (introduce==null&& length>0 ) {
			System.out.println(2);
			String picUrl=getPara("picUrl");
			String savePath = save+picUrl;
			File file = new File(savePath);	
			file.delete();
			//然后替换图片的地址
			bossmsg.set("photoUrl",photoUrl).update();
		}else {
			System.out.println(3);
			introduce =java.net.URLEncoder.encode(introduce,"utf-8");
			String picUrl=getPara("picUrl");
			String savePath = save+picUrl;
			File file = new File(savePath);	
			file.delete();
			//然后替换图片的地址
			bossmsg.set("photoUrl",photoUrl).update();
		}
		bossmsg.update();
		redirect("/bossmsg");
	}
	
	
	
	//判断 introduce 和 picurl 是否为空
	public void save() throws UnsupportedEncodingException {
		Bossmsg bossmsg=getModel(Bossmsg.class);
		System.out.println(bossmsg);
		String introduce=bossmsg.get("introduce");
		if(introduce==null){
			bossmsg.save();
		}else if(introduce!=null) {
			String enintroduce =java.net.URLEncoder.encode(introduce,"utf-8");
			bossmsg.set("introduce", enintroduce).save();
		}
		redirect("/bossmsg");
	}
	
	public void forbidden(){
		int id=Integer.parseInt(getRequest().getParameter("id")); 
		Db.update("update t_bossmsg set state=1 where id=? ",id);
		redirect("/bossmsg");
	}
	public void recover() {
		int id=getParaToInt();
		Db.update("update t_bossmsg set state=0 where id=? ",id);
		redirect("/bossmsg");
	}
}
