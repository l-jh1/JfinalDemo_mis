package edu.mis.modeles.commoditytype;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

import edu.mis.model.Commoditytype;
import edu.mis.model.Customer;
import edu.mis.model.Role;
import edu.mis.model.User;
import edu.mis.modules.user.LoginInterceptor;
import edu.mis.modules.user.LoginValidator;
import edu.mis.modules.user.UserService;

/**
 * 首页控制器
 *
 */
public class CommoditytypeController extends Controller {
	static CommoditytypeService service = new CommoditytypeService();
	public void index() {
		render("commoditytype.html");
	}
	public void list(){
		setAttr("data", service.findAllCommoditytype());
		renderJson();  
	}
	/**
	 * 注册用户视图
	 */
	public void add() {
		    //render("add.html");  //（默认对应add.html，否则需显性指定，如render("addUser.html")）
	}

	public void register(){
		Commoditytype commoditytype  =  getModel(Commoditytype.class);   //使用getModel方法获取表单值，表单中name应使用对象名.属性名，如user.id, user.password,详见jfinal手册
		if(service.register(commoditytype)){
			setAttr("result", true); 
			setAttr("msg", "增加类别成功!"); 
		}else{
			setAttr("result",false);
			setAttr("msg", "增加失败，类别名重复!"); 
		}
		renderJson(); 
	}
	
	
	public void delete() {
		setAttr("commoditytype", service.deleteCommoditytypeById(getParaToInt("id")));  //此处user应与表单中${(user.name)!}等中的user保持一致。
		setAttr("msg", "删除类别成功!"); 
	}
	public void updateName(){
		int id=this.getParaToInt("id");   //使用getPara获取表单值，与getModel方法获得表单值不同，name不能使用对象名.属性名，如user.password，而直接使用属性名，如password
		String newName = this.getPara("name");
		if(service.updateName(id, newName)){
			setAttr("result", true);
			setAttr("msg", "修改类别名成功!");
		}else{
			setAttr("result", false);
		}
		renderJson();
	}
	public void edit() {
		setAttr("commoditytype", service.findCommoditytypeById(getParaToInt("id")));  //此处user应与表单中${(user.name)!}等中的user保持一致。
	}
	
}





