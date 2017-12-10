package edu.mis.modules.user;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 *  用户登录校验器
 *  用户登录时用户名和密码不能为空，否则提交后将导致错误。
 *  也可以采用前端校验，本例演示JFinal后端校验器用法。
 */
public class LoginValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("name", "nameMsg", "请输入用户名!"); //name，nameMsg应与login.html页面表单name一致，第三个参数为提示内容
		validateRequiredString("password", "passwordMsg", "请输入密码!");
	}

	@Override
	protected void handleError(Controller c) {
		c.render("login.html"); //定义不符合校验规则视图，回到登录页面。
	}
}
