package edu.mis.core.conf;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

import edu.mis.model._MappingKit;
import edu.mis.modules.user.LoginInterceptor;

/**
 * JFinal配置
 *
 */
public class MispConfig extends JFinalConfig {

	public static void main(String[] args) {
		JFinal.start("WebRoot", 8080, "/", 5);
	}
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		PropKit.use("config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.FREE_MARKER);//Jfinal3.0版本后默认视图已经freemarker，本项目使用freemarker，需显性设置
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add(new AdminRoutes()); //可将AdminRoutes代码直接放入这里。此处仅为演示当路由多时如何将分组。
	}
	
	/**
	 * 配置模板引擎
	 */
	public void configEngine(Engine me) {
	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		_MappingKit.mapping(arp);
		me.add(arp);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.addGlobalActionInterceptor(new LoginInterceptor()); //添加全局拦截器，进行用户登录拦截
		me.add(new SessionInViewInterceptor());
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextPath")) ; //项目可使用${contextPath}指代根目录路径,见user模块页面，如user.html
	}
}
