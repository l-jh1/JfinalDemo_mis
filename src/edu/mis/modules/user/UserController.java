package edu.mis.modules.user;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

import edu.mis.model.Role;
import edu.mis.model.User;

/**
 * 用户控制器
 *
 */
public class UserController extends Controller {
	
	static UserService service = new UserService();
	
	public void index() {
		render("user.html");
	}
	
	public void list(){
		setAttr("data", service.findAllUserWithRole());
		renderJson();  
	}

	/**
	 * 注册用户视图
	 */
	public void add() {
		    //render("add.html");  //（默认对应add.html，否则需显性指定，如render("addUser.html")）

		setAttr("role",Role.dao.findAll());//授权前得到所有权限
	}

	public void register(){
		User user  =  getModel(User.class);   //使用getModel方法获取表单值，表单中name应使用对象名.属性名，如user.id, user.password,详见jfinal手册
		if(service.register(user)){
			setAttr("result", true); 
			setAttr("msg", "注册用户成功!"); 
		}else{
			setAttr("result",false);
			setAttr("msg", "注册失败，用户名重复!"); 
		}

		renderJson(); 
	}
	
	/**
	 * 修改密码视图（默认对应edit.html，否则需显性指定，如render("editPassword.html")，）
	 */
	public void edit() {
		setAttr("user", service.findUserById(getParaToInt("id")));  //此处user应与表单中${(user.name)!}等中的user保持一致。
	}
	
	public void updatePassword(){
		int id=this.getParaToInt("id");   //使用getPara获取表单值，与getModel方法获得表单值不同，name不能使用对象名.属性名，如user.password，而直接使用属性名，如password
		String newPassword = this.getPara("password");
		if(service.updatePassword(id, newPassword)){
			setAttr("result", true);
			setAttr("msg", "修改密码成功!");
		}else{
			setAttr("result", false);
		}
		renderJson();
	}
	
	/**
	 * 用户授权视图
	 */
	public void auth() {
		setAttr("user", service.findUserById(getParaToInt("id")));  //此处user应与表单中${(user.name)!}等中的user保持一致。
		setAttr("role",Role.dao.findAll());//授权前得到所有权限
	}
	
	/**
	 * 用户授权
	 */
	public void authorize(){
		int id=this.getParaToInt("id");   
		int roleId = this.getParaToInt("roleId");
		if(service.userAuthorize(id, roleId)){
			setAttr("result", true);
			setAttr("msg", "用户授权成功!");
		}else{
			setAttr("result", false);
		}
		renderJson();
	}
	
	@Before(LoginValidator.class)   //执行前进行后端校验，用户名密码是否为空
	@Clear({LoginInterceptor.class}) //因为执行前用户还未登录，应清除登录拦截器。登录拦截器为全局拦截器，所有控制器默认都会被拦截
	public void loginValidate() {
		String name = getPara("name");
		String password = getPara("password") ;
		if(service.loginValidate(name,password)){
			setSessionAttr("loginUser", name); //session记录登录用户名
			redirect("/index");
		}else{
			redirect("/login");
			setAttr("msg", "登录失败!");
		}		
	}

}


