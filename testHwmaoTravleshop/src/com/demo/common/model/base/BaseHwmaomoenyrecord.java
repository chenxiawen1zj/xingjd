package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseHwmaomoenyrecord<M extends BaseHwmaomoenyrecord<M>> extends Model<M> implements IBean {

	public M setRecordId(java.lang.Integer recordId) {
		set("recordId", recordId);
		return (M)this;
	}

	public java.lang.Integer getRecordId() {
		return get("recordId");
	}

	public M setMoneyId(java.lang.Integer moneyId) {
		set("moneyId", moneyId);
		return (M)this;
	}

	public java.lang.Integer getMoneyId() {
		return get("moneyId");
	}

	public M setCtime(java.util.Date ctime) {
		set("ctime", ctime);
		return (M)this;
	}

	public java.util.Date getCtime() {
		return get("ctime");
	}

	public M setAccount(java.lang.String account) {
		set("account", account);
		return (M)this;
	}

	public java.lang.String getAccount() {
		return get("account");
	}

}
