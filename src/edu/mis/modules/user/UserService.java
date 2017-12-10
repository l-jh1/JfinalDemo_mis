package edu.mis.modules.user;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

import edu.mis.model.User;

/**
 * 用户服务类
 *
 */
public class UserService {
	/**
	 * 查询所有商品及其权限
	 * @return
	 */
	public List<Record> findAllUserWithRole(){
		return User.dao.findAllRecord();
	}

	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public boolean register(User user){
		if(user.findUserByName(user.getName())==null){ //此处为业务逻辑，写在service里，不要写在model里。判断是否用户名重复，
			return user.save();
		}else{
			return false;
		}
		
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	public boolean updatePassword(int id,String newPassword){
		User user = findUserById(id);
		user.setPassword(newPassword);
		return user.update();
	}
	
	
	/**
	 * 登录校验
	 * @param name
	 * @param password
	 * 判断密码是否正确属于业务逻辑，应写在service中，尽管写在dao中和controller都能实现。
	 * dao中写基本的增删改查即可。dao中findUserByName可复用，不针对任何功能；
	 * controller仅仅建立模型（此处指service的loginValidate）和视图（此处指index.html,login.html）关系
	 */
	public boolean loginValidate(String name, String password){
		if(password.equals(User.dao.findUserByName(name).getPassword()))  
			return true; 
		else
			return false;
	}
	
	/**
	 * 根据ID得到用户
	 * @param id
	 * @return
	 */
	public User findUserById(int id) {
		return User.dao.findById(id);
	}
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> findAllUser(){
		return User.dao.findAll();
	}

	/**
	 * 用户授权
	 * @param id
	 * @param roleId
	 * @return
	 */
	public boolean userAuthorize(int id, int roleId) {
		User user = findUserById(id);
		user.setRoleId(roleId);
		return user.update();
	}
}
