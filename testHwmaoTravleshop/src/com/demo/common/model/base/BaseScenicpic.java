package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseScenicpic<M extends BaseScenicpic<M>> extends Model<M> implements IBean {

	public M setScenicPicId(java.lang.Integer scenicPicId) {
		set("scenicPicId", scenicPicId);
		return (M)this;
	}

	public java.lang.Integer getScenicPicId() {
		return get("scenicPicId");
	}

	public M setTittle(java.lang.String tittle) {
		set("tittle", tittle);
		return (M)this;
	}

	public java.lang.String getTittle() {
		return get("tittle");
	}

	public M setCtime(java.lang.Integer ctime) {
		set("ctime", ctime);
		return (M)this;
	}

	public java.lang.Integer getCtime() {
		return get("ctime");
	}

	public M setPicUrl(java.lang.String picUrl) {
		set("picUrl", picUrl);
		return (M)this;
	}

	public java.lang.String getPicUrl() {
		return get("picUrl");
	}

	public M setTemplateId(java.lang.Integer templateId) {
		set("templateId", templateId);
		return (M)this;
	}

	public java.lang.Integer getTemplateId() {
		return get("templateId");
	}

	public M setScenicId(java.lang.Integer scenicId) {
		set("scenicId", scenicId);
		return (M)this;
	}

	public java.lang.Integer getScenicId() {
		return get("scenicId");
	}

}
