package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseComment<M extends BaseComment<M>> extends Model<M> implements IBean {

	public M setCommentId(java.lang.Integer commentId) {
		set("commentId", commentId);
		return (M)this;
	}

	public java.lang.Integer getCommentId() {
		return get("commentId");
	}

	public M setCtime(java.lang.Integer ctime) {
		set("ctime", ctime);
		return (M)this;
	}

	public java.lang.Integer getCtime() {
		return get("ctime");
	}

	public M setState(java.lang.Integer state) {
		set("state", state);
		return (M)this;
	}

	public java.lang.Integer getState() {
		return get("state");
	}

	public M setPhotoId(java.lang.Integer photoId) {
		set("photoId", photoId);
		return (M)this;
	}

	public java.lang.Integer getPhotoId() {
		return get("photoId");
	}

	public M setContent(java.lang.String content) {
		set("content", content);
		return (M)this;
	}

	public java.lang.String getContent() {
		return get("content");
	}

	public M setFromUid(java.lang.Integer fromUid) {
		set("from_uid", fromUid);
		return (M)this;
	}

	public java.lang.Integer getFromUid() {
		return get("from_uid");
	}

	public M setTypes(java.lang.Integer types) {
		set("types", types);
		return (M)this;
	}

	public java.lang.Integer getTypes() {
		return get("types");
	}

	public M setToUid(java.lang.Integer toUid) {
		set("to_uid", toUid);
		return (M)this;
	}

	public java.lang.Integer getToUid() {
		return get("to_uid");
	}

}
