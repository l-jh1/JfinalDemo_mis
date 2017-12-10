package edu.mis.modules.blog;

import java.util.List;

import edu.mis.model.Blog;

/**
 * 日志服务类
 * 该类主要用于写业务逻辑（Blog没有复杂逻辑，只有调用dao），sql语句尽量不写在此类中，写在Model中
 * 如果不设计service层，则直接在controller中调用模型dao
 *
 */
public class BlogService {
	
	/**
	 * 添加日志
	 * @param blog
	 * @return
	 */
	public boolean saveBlog(Blog blog){  //service命名建议完整，见名知意。如此处的saveBlog，参数尽量用对象
		return blog.save();
	}
	
	/**
	 * 修改日志
	 * @param blog
	 * @return
	 */
	public boolean updateBlog(Blog blog){
		return blog.update();
	}
	
	/**
	 * 删除日志
	 * @param id
	 * @return
	 */
	public boolean deleteBlogById(int id) {
		return Blog.dao.deleteById(id);
	}
	
	/**
	 * 根据ID得到日志
	 * @param id
	 * @return
	 */
	public Blog findBlogById(int id) {
		return Blog.dao.findById(id);
	}
	
	/**
	 * 查询所有日志
	 * @return
	 */
	public List<Blog> findAllBlog(){   //本项目使用dataTables支持前端分页。如果要支持后端分页，可调用Blog.dao.paginate(pageNumber, pageSize, sqlPara)
		return Blog.dao.findAll();
	}
}
