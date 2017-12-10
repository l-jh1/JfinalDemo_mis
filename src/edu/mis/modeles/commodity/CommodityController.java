package edu.mis.modeles.commodity;

import com.jfinal.core.Controller;

import edu.mis.model.Commodity;
import edu.mis.model.Commoditytype;
import edu.mis.model.Role;


/**
 * 首页控制器
 *
 */
public class CommodityController extends Controller {
	static CommodityService service = new CommodityService();
	public void index() {
		render("commodity.html");
	}
	public void list(){
		setAttr("data", service.findAllCommodityWithType());
		renderJson();  
	}
	/**
	 * 注册用户视图
	 */
	public void add() {
		    //render("add.html");  //（默认对应add.html，否则需显性指定，如render("addUser.html")）
		setAttr("commoditytype",Commoditytype.dao.findAll());//授权前得到所有权限
	}

	public void register(){
		Commodity commodity  =  getModel(Commodity.class);   //使用getModel方法获取表单值，表单中name应使用对象名.属性名，如user.id, user.password,详见jfinal手册
		if(service.register(commodity)){
			setAttr("result", true); 
			setAttr("msg", "增加商品成功!"); 
		}else{
			setAttr("result",false);
			setAttr("msg", "增加失败，商品名重复!"); 
		}
		renderJson(); 
	}
	
	
	public void delete() {
		if(service.deleteCommodityById(getParaToInt("id"))){
			setAttr("result", true); 
			setAttr("msg", "删除商品成功!"); 
		}else{
			setAttr("result",false);
			setAttr("msg", "删除商品失败!"); 
		}  //此处user应与表单中${(user.name)!}等中的user保持一致。
		renderJson(); 
	}
	public void updateName(){
		int id=this.getParaToInt("id");   //使用getPara获取表单值，与getModel方法获得表单值不同，name不能使用对象名.属性名，如user.password，而直接使用属性名，如password
		Double price = Double.valueOf(this.getPara("price"));//不改名字，改价格
		if(service.updateName(id, price)){
			setAttr("result", true);
			setAttr("msg", "修改价格成功!");
		}else{
			setAttr("result", false);
		}
		renderJson();
	}
	public void edit() {
		setAttr("commodity", service.findCommodityById(getParaToInt("id")));  //此处user应与表单中${(user.name)!}等中的user保持一致。
	}
	/**
	 * 用户授权
	 */
	
	public void auth() {
		setAttr("commodity", service.findCommodityById(getParaToInt("id")));  //此处user应与表单中${(user.name)!}等中的user保持一致。
		setAttr("commoditytype",Commoditytype.dao.findAll());//授权前得到所有权限
	}
	/**
	 * 用户授权视图
	 */
	public void authorize(){
		int id=this.getParaToInt("id");   
		int typeId = this.getParaToInt("typeId");
		if(service.commodityAuthorize(id, typeId)){
			setAttr("result", true);
			setAttr("msg", "类型更改成功!");
		}else{
			setAttr("result", false);
		}
		renderJson();
	}
	
}





