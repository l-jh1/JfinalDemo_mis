package edu.mis.modeles.commodity;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

import edu.mis.model.Commodity;
import edu.mis.model.Commoditytype;;

/**
 * 用户服务类
 *
 */
public class CommodityService {
	
	/**
	 * 注册
	 * @param customer
	 * @return
	 */
	public boolean register(Commodity commodity){
		if(commodity.findCommodityByName(commodity.getCommodityName())==null){ //此处为业务逻辑，写在service里，不要写在model里。判断是否用户名重复，
			return commodity.save();
		}else{
			return false;
		}
		
	}
	
	
	/**
	 * 根据ID得到商品
	 * @param id
	 * @return
	 */
	public Commodity findCommodityById(int id) {
		return Commodity.dao.findById(id);
	}
	
	public boolean deleteCommodityById(int id) {
		return Commodity.dao.deleteById(id);
	}
	public boolean updateName(int id,Double price){
		Commodity commodity = findCommodityById(id);
		commodity.setPrice(price);
		return commodity.update();
	}

	/**
	 * 查询所有商品
	 * @return
	 */
	public List<Commodity> findAllCommodity(){
		return Commodity.dao.findAll();
	}
	/**
	 * 查询所有商品及其类别
	 * @return
	 */
	public List<Record> findAllCommodityWithType(){
		return Commodity.dao.findAllRecord();
	}
	/**
	 * 查询商品类型更改
	 * @return
	 */

	public boolean commodityAuthorize(int id, int type) {
		Commodity commodity = findCommodityById(id);
		commodity.setType(type);
		return commodity.update();
	}
}
