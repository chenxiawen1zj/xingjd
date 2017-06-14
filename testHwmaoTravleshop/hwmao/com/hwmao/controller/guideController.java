/**
 * 
 */
package com.hwmao.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.demo.common.model.Agent;
import com.demo.common.model.Guide;
import com.demo.common.model.Photo;
import com.demo.common.model.Scenic;
import com.demo.common.model.Scenicboard;
import com.hwmao.util.Contants;
import com.hwmao.util.SQLUtils;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author Administrator
 *
 */
public class guideController extends Controller {
	String save=Contants.savepath;
	
	public void index() throws UnsupportedEncodingException {
	
		render("/hwmao/guide/guide.html");
		
	}
	public void add() {
		//返回代理商的名字，商家的名字
		
			Object scenicId=getSession().getAttribute("User");
			Scenic scenic=Scenic.dao.findById(scenicId);
			Integer agentId=scenic.getAgentId();
			String agentName=Agent.dao.findById(agentId).getAgentname();
			setAttr("scenic", scenic.put("agentName", agentName));
			System.out.println( scenic.put("agentName", agentName));
			List<Scenicboard> scenicboards =new Scenicboard().find("select * from t_scenicboard where scenicId=? and types=0",scenicId);
			System.out.println();
			setAttr("scenicboard", scenicboards);
			render("/hwmao/guide/add.html");
			
		
	}
	public void save() {
		// 编辑器里面的内容
		String content = getRequest().getParameter("content");
		String editorValue = getRequest().getParameter("picturl");
		String sceninBoardId = getRequest().getParameter("sceninBoardId");
		int sceninBoardId1=Integer.parseInt(sceninBoardId);
		String[] sourceStrArray = editorValue.split(",");
		String piurl=sourceStrArray[0].substring(sourceStrArray[0].indexOf("/") + 1);
		// 文章的标题
		String tittle = getRequest().getParameter("tittle");
		Guide guide=new Guide();
		guide.set("picurl", piurl).set("tittle", tittle).set("content", content).set("sceninBoardId", sceninBoardId1).save();
		render("/hwmao/guide/guide.html");
	}
	//返回提现页面的数据
		public void  GetDraw() throws UnsupportedEncodingException {

		
			int pageSize = Integer.parseInt(getRequest().getParameter("pageSize"));  
	        int page = Integer.parseInt(getRequest().getParameter("page"));
	        String sqlstr ="from t_guide";
	        Page<Guide> aEs =Guide.dao.paginate(page, pageSize,sqlstr);
	        System.out.println(aEs.getPageSize());
	        System.out.println(aEs.getList());
			setAttr("totalRow", aEs.getTotalRow());
			if (aEs.getList().size()>0) {
				setAttr("content", aEs.getList());
			}
			
			render("/hwmao/guide/guide.html");
		}
	//编辑
	public void edit(){
		//这条数据的id
		int id=getParaToInt();
		Guide guide=new Guide().findGuide(id);
		setAttr("guide", guide);
		System.out.println(guide);
		render("/hwmao/guide/edit.html");
	}
	public void delpic(String picUrl) {
		String savePath = save+picUrl;
		File file = new File(savePath);	
		System.out.println(file);
		file.delete();
	}
	public void  update() {
		String picturl = getRequest().getParameter("picturl");
		String bepicurl = getRequest().getParameter("bepicurl");
		Guide guide=getModel(Guide.class);
		if (picturl != null && picturl.length() != 0) {
			 delpic(bepicurl); 
			 String[] sourceStrArray = picturl.split(",");
			 String piurl=sourceStrArray[0].substring(sourceStrArray[0].indexOf("/") + 1);
			 guide.set("picurl", piurl).update();
			 renderJson("success");
			 return;
		 }
		guide.set("picurl", bepicurl).update();
		renderJson("success");
	}
	
	public void del() {
		int id = Integer.parseInt(getRequest().getParameter("id"));  
		Guide guide=new Guide().findById(id);
		String picurl=guide.get("picurl");
		delpic(picurl); 
		guide.delete();
		renderJson(1);
	}
	
	//用户上传图片后没有完成，取消后，先删除图片
		public void cancle() {
			
			String editorValue = getRequest().getParameter("pictureSrc");
			String[] sourceStrArray = editorValue.split(",");
			String piurl=sourceStrArray[0].substring(sourceStrArray[0].indexOf("/") + 1);
			String savePath = save+piurl;
			File file = new File(savePath);	
			System.out.println(file);
			file.delete();
			renderJson(1);
		}
}
