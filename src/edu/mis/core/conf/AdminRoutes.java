package edu.mis.core.conf;

import com.jfinal.config.Routes;

import edu.mis.modeles.commodity.CommodityController;
import edu.mis.modeles.commoditytype.CommoditytypeController;
import edu.mis.modeles.cusomter.CustomerController;
import edu.mis.modules.blog.BlogController;
import edu.mis.modules.index.IndexController;
import edu.mis.modules.role.RoleController;
import edu.mis.modules.user.UserController;

/**
 * 后台路由
 *
 */
public class AdminRoutes extends Routes{

	@Override
	public void config() {
		add("/", IndexController.class, "/admin");	// 第三个参数为该Controller的视图存放路径，省略则与第一个参数相同
		add("/blog", BlogController.class,"/admin/blog");	//如省略第三个参数，则指向“webroot/blog/XXX.html”,会报错找不到页面
		add("/user", UserController.class,"/admin/user");
		add("/role", RoleController.class,"/admin/role");	
		add("/customer", CustomerController.class,"/admin/customer");	
		add("/commoditytype", CommoditytypeController.class,"/admin/commoditytype");	
		add("/commodity", CommodityController.class,"/admin/commodity");	
	}

}
