package com.demo.common;

import com.demo.blog.BlogController;
import com.demo.common.model._MappingKit;
import com.demo.index.IndexController;
import com.hwmao.controller.LoginController;
import com.hwmao.controller.agentController;
import com.hwmao.controller.bossmsgController;
import com.hwmao.controller.bossuserController;
import com.hwmao.controller.commentController;
import com.hwmao.controller.feedback;
import com.hwmao.controller.firstController;
import com.hwmao.controller.guideController;
import com.hwmao.controller.indexController;
import com.hwmao.controller.moneyController;
import com.hwmao.controller.orderController;
import com.hwmao.controller.photoController;
import com.hwmao.controller.scenicBoardController;
import com.hwmao.controller.scenicController;
import com.hwmao.controller.scenicPicController;
import com.hwmao.controller.tableController;
import com.hwmao.controller.test1Contoller;

import com.hwmao.controller.uploadController;
import com.hwmao.controller.user;
import com.hwmao.nio.HelloServerStarter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
	 * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
	 * -XX:PermSize=64M -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		JFinal.start("WebRoot", 80, "/", 5);

		/**
		 * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
		 */
		// JFinal.start("WebRoot", 80, "/");
	}
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", true));
		me.setBaseUploadPath("d:/tomcat/apache-tomcat-8080/webapps/HwmaoTravleshop/temp");
		/*String serverPath = "d:/tomcat/apache-tomcat-8080/webapps/HwmaoTravleshop/temp";*/
		/*d:/apache-tomcat-7.0.75/webapps/HwmaoTravleshop/temp*/
		/*d:/tomcat/apache-tomcat-8088/webapps/HwmaoTravleshop/temp*/
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", LoginController.class, "/index");	// 第三个参数为该Controller的视图存放路径
		me.add("/blog", BlogController.class);
		/*me.add("/main",hwmaoController.class);// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"*/
		me.add("/test1",test1Contoller.class);
		me.add("/administrator",tableController.class);// 管理员控制器
		me.add("/agent",agentController.class,"/HwmaoTravleagent");
		me.add("/hwmao",LoginController.class);
		me.add("/scenic",scenicController.class);
		me.add("/order",orderController.class);
		me.add("/scenicpic",scenicPicController.class);
		me.add("/photo",photoController.class);
		me.add("/scenicboard",scenicBoardController.class);
		me.add("/upload",uploadController.class);
		me.add("/user",user.class);
		me.add("/feedback",feedback.class);
		me.add("/comment",commentController.class);
		me.add("/index",indexController.class);
		me.add("/bossuser",bossuserController.class);
		me.add("/bossmsg",bossmsgController.class);
		me.add("/money",moneyController.class);
		me.add("/first",firstController.class);
		me.add("/guide",guideController.class);
		
		/*me.add("/login",LoginController.class);*/
		
	}
	
	public void configEngine(Engine me) {
		me.addSharedObject("IMG_HOST_LOCAL", "https://api.hwmlite.com");//本地测试地址
		/*me.addSharedObject("path", object)*/
		/*me.addSharedObject("IMG_HOST_PROD", "https://demo.hwmlite.com");//正式测试地址
		me.addSharedObject("IMG_HOST_PRODTEST", "https://api.hwmlite.com");//线上测试测试地址
*/	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
	/*	//缓存 user模块到 redis
		RedisPlugin userRedis=new RedisPlugin("userCache", "192.168.1.17");
		me.add(userRedis);*/
		
		//配置缓存插件
		me.add(new EhCachePlugin(PathKit.getRootClassPath()+"\\ehcache.xml"));
		RedisPlugin userRedis = new RedisPlugin("userCache", "127.0.0.1",6379);
		me.add(userRedis);
		
		/*me.add(new HelloServerStarter());*/

	
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		 me.add(new MyInterceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextPath")); // 设置上下文路径
	}
}
