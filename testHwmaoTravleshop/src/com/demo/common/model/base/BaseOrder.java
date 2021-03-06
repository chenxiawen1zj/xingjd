package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseOrder<M extends BaseOrder<M>> extends Model<M> implements IBean {

	public M setOrderId(java.lang.Integer orderId) {
		set("orderId", orderId);
		return (M)this;
	}

	public java.lang.Integer getOrderId() {
		return get("orderId");
	}

	public M setMerchandiseName(java.lang.String merchandiseName) {
		set("merchandiseName", merchandiseName);
		return (M)this;
	}

	public java.lang.String getMerchandiseName() {
		return get("merchandiseName");
	}

	public M setNumber(java.lang.Integer number) {
		set("number", number);
		return (M)this;
	}

	public java.lang.Integer getNumber() {
		return get("number");
	}

	public M setDistribution(java.lang.Integer distribution) {
		set("distribution", distribution);
		return (M)this;
	}

	public java.lang.Integer getDistribution() {
		return get("distribution");
	}

	public M setPrice(java.lang.Double price) {
		set("price", price);
		return (M)this;
	}

	public java.lang.Double getPrice() {
		return get("price");
	}

	public M setOrderStatus(java.lang.Integer orderStatus) {
		set("orderStatus", orderStatus);
		return (M)this;
	}

	public java.lang.Integer getOrderStatus() {
		return get("orderStatus");
	}

	public M setTime(java.lang.String time) {
		set("time", time);
		return (M)this;
	}

	public java.lang.String getTime() {
		return get("time");
	}

	public M setPayStatus(java.lang.Integer payStatus) {
		set("payStatus", payStatus);
		return (M)this;
	}

	public java.lang.Integer getPayStatus() {
		return get("payStatus");
	}

	public M setCtime(java.lang.String ctime) {
		set("ctime", ctime);
		return (M)this;
	}

	public java.lang.String getCtime() {
		return get("ctime");
	}

}
