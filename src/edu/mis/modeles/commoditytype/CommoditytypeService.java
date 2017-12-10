package edu.mis.modeles.commoditytype;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

import edu.mis.model.Commoditytype;
import edu.mis.model.Customer;
import edu.mis.model.User;

/**
 * 用户服务类
 *
 */
public class CommoditytypeService {
	
	/**
	 * 注册
	 * @param Commoditytype
	 * @return
	 */
	public boolean register(Commoditytype commoditytype){
		if(commoditytype.findCommoditytypeByName(commoditytype.getName())==null){ //此处为业务逻辑，写在service里，不要写在model里。判断是否用户名重复，
			return commoditytype.save();
		}else{
			return false;
		}
		
	}
	
	
	/**
	 * 根据ID得到用户
	 * @param id
	 * @return
	 */
	public Commoditytype findCommoditytypeById(int id) {
		return Commoditytype.dao.findById(id);
	}
	
	public boolean deleteCommoditytypeById(int id) {
		return Commoditytype.dao.deleteById(id);
	}
	public boolean updateName(int id,String newName){
		Commoditytype commoditytype = findCommoditytypeById(id);
		commoditytype.setName(newName);
		return commoditytype.update();
	}

	/**
	 * 查询所有用户
	 * @return
	 */
	public List<Commoditytype> findAllCommoditytype(){
		return Commoditytype.dao.findAll();
	}

}
