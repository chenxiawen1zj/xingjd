package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseAddress<M extends BaseAddress<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setUnion(java.lang.String union) {
		set("union", union);
		return (M)this;
	}

	public java.lang.String getUnion() {
		return get("union");
	}

	public M setAddress(java.lang.String address) {
		set("address", address);
		return (M)this;
	}

	public java.lang.String getAddress() {
		return get("address");
	}

	public M setTelephone(java.lang.String telephone) {
		set("telephone", telephone);
		return (M)this;
	}

	public java.lang.String getTelephone() {
		return get("telephone");
	}

	public M setCtime(java.lang.String ctime) {
		set("ctime", ctime);
		return (M)this;
	}

	public java.lang.String getCtime() {
		return get("ctime");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}

	public java.lang.String getName() {
		return get("name");
	}

	public M setSelection(java.lang.Integer selection) {
		set("selection", selection);
		return (M)this;
	}

	public java.lang.Integer getSelection() {
		return get("selection");
	}

}
