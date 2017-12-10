package edu.mis.model;

import java.util.List;

import edu.mis.model.base.BaseBlog;

@SuppressWarnings("serial")
public class Blog extends BaseBlog<Blog> {  //jfinal中model相当于JAVAEE框架中的DAO。baseModel相当于POJO。如果有model，重新运行代码生成器，不会重复生成model，以避免扩展的dao被覆盖
	public static final Blog dao = new Blog(); 
	/**
	 * 查询所有日志
	 * @return
	 */
	public List<Blog> findAll(){
		return dao.find("SELECT * FROM blog");
	}
}


