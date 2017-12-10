package edu.mis.modules.role;

import com.jfinal.core.Controller;

/**
 * 权限控制器
 *
 */
public class RoleController extends Controller {
	
	static RoleService service = new RoleService();
	
	public void index() {
		render("role.html");
	}
	
	public void list(){
		setAttr("data", service.findAllRole());
		renderJson();  
	}

}


