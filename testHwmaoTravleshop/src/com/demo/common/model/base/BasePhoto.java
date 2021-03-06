package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BasePhoto<M extends BasePhoto<M>> extends Model<M> implements IBean {

	public M setPhotoId(java.lang.Integer photoId) {
		set("photoId", photoId);
		return (M)this;
	}

	public java.lang.Integer getPhotoId() {
		return get("photoId");
	}

	public M setPhotoUrl(java.lang.String photoUrl) {
		set("photoUrl", photoUrl);
		return (M)this;
	}

	public java.lang.String getPhotoUrl() {
		return get("photoUrl");
	}

	public M setUnion(java.lang.String union) {
		set("union", union);
		return (M)this;
	}

	public java.lang.String getUnion() {
		return get("union");
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

	public M setIntroduce(java.lang.String introduce) {
		set("introduce", introduce);
		return (M)this;
	}

	public java.lang.String getIntroduce() {
		return get("introduce");
	}

	public M setSelection(java.lang.Integer selection) {
		set("selection", selection);
		return (M)this;
	}

	public java.lang.Integer getSelection() {
		return get("selection");
	}

	public M setScenicId(java.lang.Integer scenicId) {
		set("scenicId", scenicId);
		return (M)this;
	}

	public java.lang.Integer getScenicId() {
		return get("scenicId");
	}

	public M setNickname(java.lang.String nickname) {
		set("nickname", nickname);
		return (M)this;
	}

	public java.lang.String getNickname() {
		return get("nickname");
	}

	public M setTypes(java.lang.Integer types) {
		set("types", types);
		return (M)this;
	}

	public java.lang.Integer getTypes() {
		return get("types");
	}

	public M setSceninBoardId(java.lang.Integer sceninBoardId) {
		set("sceninBoardId", sceninBoardId);
		return (M)this;
	}

	public java.lang.Integer getSceninBoardId() {
		return get("sceninBoardId");
	}

	public M setAvatarUrl(java.lang.String avatarUrl) {
		set("avatarUrl", avatarUrl);
		return (M)this;
	}

	public java.lang.String getAvatarUrl() {
		return get("avatarUrl");
	}

	public M setCommentaccount(java.lang.Integer commentaccount) {
		set("commentaccount", commentaccount);
		return (M)this;
	}

	public java.lang.Integer getCommentaccount() {
		return get("commentaccount");
	}

	public M setSupportaccount(java.lang.Integer supportaccount) {
		set("supportaccount", supportaccount);
		return (M)this;
	}

	public java.lang.Integer getSupportaccount() {
		return get("supportaccount");
	}

}
