/**
 * 
 */
package com.hwmao.controller;




import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.demo.common.model.Photo;
import com.demo.common.model.Scenic;
import com.demo.common.model.Scenicboard;
import com.demo.common.model.wxaqrcode;
import com.hwmao.util.CommonUtils;
import com.hwmao.util.Contants;
import com.hwmao.util.ImgErToFileUtil;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.template.expr.ast.Field;

/**
 * @author shop
 *
 */
public class scenicBoardController extends Controller {
	String save=Contants.savepath;
	
	public void index() {
		
		render("/hwmao/scenicboard/scenicboardtable.html");
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
        	sqlstr = "from t_scenicboard  where scenicId="+scenicId+" order by "+sort+" "+order;
        	
        }else{
        	search=java.net.URLDecoder.decode(search,"utf-8");
        	sqlstr = "from t_scenicboard where scenicId="+scenicId+" and tittle like '%"+search+"%' order by "+sort+" "+order;
        }
        Page<Scenicboard> aEs =Scenicboard.dao.paginate(pageNumber, pageSize,sqlstr);
        java.util.List<Scenicboard> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);
	}
	public void add() {
		//scenicid
		Object scenicId=getSession().getAttribute("User");
		setAttr("scenic", Scenic.dao.findById(scenicId));
		System.out.println(Scenic.dao.findById(scenicId));
		render("/hwmao/scenicboard/scenicboardadd.html");
	}
	
	public void checkscenicboardremain(){
		Object scenicId=getSession().getAttribute("User");
		//判断商户的剩余是否足够  1 足够  0不足够
		int enough=new Scenic().findscenicboardremain(scenicId);
		if (enough==0) {
			//不足够
			 renderJson(0);
		}else {
			//足够
			 renderJson(1);	
		}
	}
	
	
	
	
	public void update() {
		System.out.println(getModel(Scenicboard.class));
		getModel(Scenicboard.class).update();
		redirect("/scenicboard");
	}
	
	public void del() {
		
	}
	public void setScenicBoardId() {
		int id=Integer.parseInt(getRequest().getParameter("id")); 
		setAttr("id", id);
		render("/hwmao/scenicboard/scenicboardpicmgmt.html");
	}
	
	
	public void findScenicboardpic() throws UnsupportedEncodingException {
		int sceninBoardId=Integer.parseInt(getRequest().getParameter("id")); 
		int pageSize = Integer.parseInt(getRequest().getParameter("limit"));  
        int pageNumber = Integer.parseInt(getRequest().getParameter("offset"));
        String sort=getRequest().getParameter("sort");
        String search=getRequest().getParameter("search");	        
        String order=getRequest().getParameter("order");
        String sqlstr ="";
        if(search==null){
        	sqlstr = "from t_photo where sceninBoardId="+sceninBoardId+" order by "+sort+" "+order;
        	
        }else{
        	sqlstr = "from t_photo where tel like '%"+search+"%' order by "+sort+" "+order;
        }
        Page<Photo> aEs =Photo.dao.paginate(pageNumber, pageSize,sqlstr);
        java.util.List<Photo> a=aEs.getList();
        String sellRecordJson=JsonKit.toJson(a);
        int total=aEs.getTotalRow();
        String json = "{\"total\":" + total + ",\"rows\":" + sellRecordJson + "}"; 
        System.out.println(json);
        renderJson(json);	
	
	}
	  /*  * @return 
	    *      1：保存正常
	    *      0：保存失败*/
	public void save() throws UnsupportedEncodingException {
		int ctime=new com.hwmao.util.CommonUtils().getNowDateTime();	
		Object scenicId=getSession().getAttribute("User");
		int agentId=new Scenic().findById(scenicId).getAgentId();
		Scenicboard scenicboard=getModel(Scenicboard.class).set("scenicId", scenicId).set("ctime", ctime).set("agentId", agentId);
		//需要制作的哪款二维码 0基本款 1最新款 2两都生成
		String qrtypes=getRequest().getParameter("qrtypes");
		
		
		scenicboard.save();
		//然后去scenicboardremain 里面减1
		Db.update("update t_scenic set scenicboardremain=scenicboardremain-1 where scenicId=?",scenicId);
		int types=scenicboard.getInt("types");
		if (types==1) {
			//哪个景点
			int i=scenicboard.get("sceninBoardId");
			//生成哪个款的二维码
			int qrtypes1 = Integer.parseInt(qrtypes);
			if (qrtypes1==0) {
				createwxaqrcode(i);
				redirect("/scenicboard");
			}
			if (qrtypes1 == 1 ){
				createspecialcode(i);
				redirect("/scenicboard");
			}
		}
		redirect("/scenicboard");
	}
	
	public void edit() {
		setAttr("scenicboard", Scenicboard.dao.findById(getParaToInt()));
		System.out.println("abc"+Scenicboard.dao.findById(getParaToInt()));
		render("/hwmao/scenicboard/scenicboardedit.html");
	}
	
	
	public void forbidden(){
		int id=Integer.parseInt(getRequest().getParameter("id")); 
		Db.update("update t_scenicboard set state=1 where sceninBoardId=? ",id);
		redirect("/scenicboard");
	}
	public void recover() {
		int id=getParaToInt();
		Db.update("update t_scenicboard set state=0 where sceninBoardId=? ",id);
		redirect("/scenicboard");
	}
	  /*  * @return 
	    *      1：保存正常
	    *      0：保存失败*/
	
	//生成普通的二维码
	public int createwxaqrcode(int id) throws UnsupportedEncodingException {
		String path="pages/splash/splash?id="+id;
		String aaccess_token =CacheKit.get("access_token","access_token");
		if (aaccess_token==null) {
			aaccess_token=new CommonUtils().token();
		}
		int string=new CommonUtils().createwxaqrcode(save,path,aaccess_token,id);
		return string;
	}
	
	//生成特殊的二维码
	public int createspecialcode(int id) throws UnsupportedEncodingException {
		String path="pages/splash/splash?id="+id;
		String aaccess_token =CacheKit.get("access_token","access_token");
		if (aaccess_token==null) {
			aaccess_token=new CommonUtils().token();
		}
		int string=new CommonUtils().createspecialcode(save,path,aaccess_token,id);
		return string;
	}
	//更新普通的二维码
		public int updatewxaqrcode(int id) throws UnsupportedEncodingException {
			String path="pages/splash/splash?id="+id;
			String aaccess_token =CacheKit.get("access_token","access_token");
			if (aaccess_token==null) {
				aaccess_token=new CommonUtils().token();
			}
			int string=new CommonUtils().updatewxaqrcode(save,path,aaccess_token,id);
			return string;
		}
	//更新特殊的二维码
		public int updatespecialcode(int id) throws UnsupportedEncodingException {
			String path="pages/splash/splash?id="+id;
			String aaccess_token =CacheKit.get("access_token","access_token");
			if (aaccess_token==null) {
				aaccess_token=new CommonUtils().token();
			}
			int string=new CommonUtils().updatespecialcode(save,path,aaccess_token,id);
			return string;
		}
	//已经好了已经再生成
	public void addwxaqrcode() throws UnsupportedEncodingException {
		//微信二维码
		int id=Integer.parseInt(getRequest().getParameter("id")); 
		updatewxaqrcode(id);
		renderJson(1);
	}
	
	//已经好了已经再生成
	public void addspecialcodewxaqrcode() throws UnsupportedEncodingException {
		//微信二维码
		int id=Integer.parseInt(getRequest().getParameter("id")); 
		updatespecialcode(id);

		renderJson(1);
	}
	

}
