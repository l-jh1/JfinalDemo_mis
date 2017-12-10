package edu.mis.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRole<M extends BaseRole<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setRoleName(java.lang.String roleName) {
		set("roleName", roleName);
		return (M)this;
	}

	public java.lang.String getRoleName() {
		return get("roleName");
	}

}
