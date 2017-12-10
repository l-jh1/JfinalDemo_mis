package edu.mis.modules.index;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

import edu.mis.modules.user.LoginInterceptor;

/**
 * 首页控制器
 *
 */
public class IndexController extends Controller {
	public void index() {
		render("index.html");
	}
	
	public void help() {
		render("help.html");
	}

	@Clear({LoginInterceptor.class}) //清除拦截器，即该方法不会被LoginInterceptor拦截，用在没有登录也允许访问的控制器方法上。
	public void login() {
		render("user/login.html");
	}
	
	@Clear({LoginInterceptor.class})
	public void logout() {
		getSession().removeAttribute("loginUser");
		getSession().invalidate();
		redirect("/login");	
	}
}





