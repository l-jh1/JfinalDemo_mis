package edu.mis.modules.user;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
/**
 * 登录拦截器
 * 判断用户是否登录，没登录则进行拦截。
 * 可使用clear注解清除拦截。详见IndexController注释
 */
public class LoginInterceptor implements Interceptor{
	public void intercept(Invocation invocation){
		Controller controller=invocation.getController();	
		String userName=controller.getSessionAttr("loginUser"); //从session中得到登录用户名
		if(userName==null){
			controller.redirect("/login");  //跳转到登录页
		}
		else{
			invocation.invoke();  //拦截校验通过，继续执行
		}
	}
}
