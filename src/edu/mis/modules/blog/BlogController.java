package edu.mis.modules.blog;

import com.jfinal.core.Controller;

import edu.mis.model.Blog;

/**
 * 日志控制器
 *
 */
public class BlogController extends Controller {
	
	static BlogService service = new BlogService();
	
	/**
	 * 日志列表视图页面
	 */
	public void index() {   //如果为index，则访问地址为"路由名/" ,此处为"blog/"
		render("blog.html");
	}
	
	/**
	 * 日志列表后台json
	 */
	
	public void list(){   //访问地址为 blog/list
		setAttr("data", service.findAllBlog());
		renderJson();   //可直接在浏览器输入"localhost:8080/blog/list"查看返回的Json值，可以作为调试的方法
	}


	/**
	 * 添加日志
	 */
	public void save(){
		Blog blog  =  getModel(Blog.class); //注意：使用getModel方法获取表单值非常方便，不需要对每个字段进行设置，直接得到model。注意使用此方法前端表单name必须名称为“对象名.属性名”，如blog.title   其中 blog对应Blog类，title对应Blog类的title属性
		if(service.saveBlog(blog)){
			setAttr("result", true); //前端ajax回调值，进行交互判断  if(data.result){.......} 注意两个result对应
			setAttr("msg", "添加公告成功!"); //前端ajax回调值，交互消息框layer.msg(data.msg)
		}else{
			setAttr("result",false);
		}
		renderJson();  //返回json，用于向前台页面返回结果
	}
	
	public void edit() {
		setAttr("blog", service.findBlogById(getParaToInt("id")));  //getPara获取参数为string类型，getParaToInt将获得的String转为int
		renderJson();
	}
	
	/**
	 * 修改日志
	 */
	public void update(){
		Blog blog  =  getModel(Blog.class);
		if(service.updateBlog(blog)){
			setAttr("result", true);
			setAttr("msg", "修改公告成功!");
		}else{
			setAttr("result", false);
		}
		renderJson();
	}
	
	/**
	 * 删除日志
	 * 测试访问地址：localhost:8080/blog/delete?id=1
	 */
	public void delete() {
		if(service.deleteBlogById(getParaToInt("id"))){
			setAttr("result", true);
			setAttr("msg", "删除公告成功!");
		}else{
			setAttr("result", false);
		}
		renderJson();
	}
}


