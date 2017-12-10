package edu.mis.modules.role;

import java.util.List;

import edu.mis.model.Role;

/**
 * 权限服务类
 *
 */
public class RoleService {
	
	/**
	 * 查询所有权限
	 * @return
	 */
	public List<Role> findAllRole(){
		return Role.dao.findAll();
	}
}
