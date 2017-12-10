package edu.mis.modeles.cusomter;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

import edu.mis.model.Customer;
import edu.mis.model.User;

/**
 * 用户服务类
 *
 */
public class CustomerService {
	
	/**
	 * 注册
	 * @param customer
	 * @return
	 */
	public boolean register(Customer customer){
		if(customer.findCustomerByName(customer.getName())==null){ //此处为业务逻辑，写在service里，不要写在model里。判断是否用户名重复，
			return customer.save();
		}else{
			return false;
		}
		
	}
	
	
	/**
	 * 根据ID得到用户
	 * @param id
	 * @return
	 */
	public Customer findCustomerById(int id) {
		return Customer.dao.findById(id);
	}
	
	public boolean deleteCustomerById(int id) {
		return Customer.dao.deleteById(id);
	}
	public boolean updateName(int id,String newName){
		Customer customer = findCustomerById(id);
		customer.setName(newName);
		return customer.update();
	}

	/**
	 * 查询所有用户
	 * @return
	 */
	public List<Customer> findAllCustomer(){
		return Customer.dao.findAll();
	}
	/**
	 * 查询所有商品及其权限
	 * @return
	 */

	public boolean userAuthorize(int id, int roleId) {
		Customer user = findCustomerById(id);
		return user.update();
	}
}
