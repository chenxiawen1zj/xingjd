package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseOperatelog<M extends BaseOperatelog<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setOlType(java.lang.Integer olType) {
		set("OL_Type", olType);
		return (M)this;
	}

	public java.lang.Integer getOlType() {
		return get("OL_Type");
	}

	public M setOlModule(java.lang.Integer olModule) {
		set("OL_Module", olModule);
		return (M)this;
	}

	public java.lang.Integer getOlModule() {
		return get("OL_Module");
	}

	public M setOlContent(java.lang.String olContent) {
		set("OL_Content", olContent);
		return (M)this;
	}

	public java.lang.String getOlContent() {
		return get("OL_Content");
	}

	public M setOlOpenid(java.lang.String olOpenid) {
		set("OL_openId", olOpenid);
		return (M)this;
	}

	public java.lang.String getOlOpenid() {
		return get("OL_openId");
	}

	public M setOlAdddate(java.lang.String olAdddate) {
		set("OL_AddDate", olAdddate);
		return (M)this;
	}

	public java.lang.String getOlAdddate() {
		return get("OL_AddDate");
	}

	public M setOlIp(java.lang.String olIp) {
		set("OL_IP", olIp);
		return (M)this;
	}

	public java.lang.String getOlIp() {
		return get("OL_IP");
	}

}
