package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseAgent<M extends BaseAgent<M>> extends Model<M> implements IBean {

	public M setAgentId(java.lang.Integer agentId) {
		set("agentId", agentId);
		return (M)this;
	}

	public java.lang.Integer getAgentId() {
		return get("agentId");
	}

	public M setCtime(java.lang.String ctime) {
		set("ctime", ctime);
		return (M)this;
	}

	public java.lang.String getCtime() {
		return get("ctime");
	}

	public M setMoney(java.lang.Integer money) {
		set("money", money);
		return (M)this;
	}

	public java.lang.Integer getMoney() {
		return get("money");
	}

	public M setProvince(java.lang.String province) {
		set("province", province);
		return (M)this;
	}

	public java.lang.String getProvince() {
		return get("province");
	}

	public M setScenicaccount(java.lang.Integer scenicaccount) {
		set("scenicaccount", scenicaccount);
		return (M)this;
	}

	public java.lang.Integer getScenicaccount() {
		return get("scenicaccount");
	}

	public M setAgentname(java.lang.String agentname) {
		set("agentname", agentname);
		return (M)this;
	}

	public java.lang.String getAgentname() {
		return get("agentname");
	}

	public M setCity(java.lang.String city) {
		set("city", city);
		return (M)this;
	}

	public java.lang.String getCity() {
		return get("city");
	}

	public M setTypes(java.lang.Integer types) {
		set("types", types);
		return (M)this;
	}

	public java.lang.Integer getTypes() {
		return get("types");
	}

	public M setScenicboardaccount(java.lang.Integer scenicboardaccount) {
		set("scenicboardaccount", scenicboardaccount);
		return (M)this;
	}

	public java.lang.Integer getScenicboardaccount() {
		return get("scenicboardaccount");
	}

	public M setState(java.lang.Integer state) {
		set("state", state);
		return (M)this;
	}

	public java.lang.Integer getState() {
		return get("state");
	}

	public M setLoginname(java.lang.String loginname) {
		set("loginname", loginname);
		return (M)this;
	}

	public java.lang.String getLoginname() {
		return get("loginname");
	}

	public M setPassword(java.lang.String password) {
		set("password", password);
		return (M)this;
	}

	public java.lang.String getPassword() {
		return get("password");
	}

	public M setContactname(java.lang.String contactname) {
		set("contactname", contactname);
		return (M)this;
	}

	public java.lang.String getContactname() {
		return get("contactname");
	}

	public M setContacttel(java.lang.String contacttel) {
		set("contacttel", contacttel);
		return (M)this;
	}

	public java.lang.String getContacttel() {
		return get("contacttel");
	}

	public M setBankname(java.lang.String bankname) {
		set("bankname", bankname);
		return (M)this;
	}

	public java.lang.String getBankname() {
		return get("bankname");
	}

	public M setBankaccount(java.lang.String bankaccount) {
		set("bankaccount", bankaccount);
		return (M)this;
	}

	public java.lang.String getBankaccount() {
		return get("bankaccount");
	}

	public M setBankaddress(java.lang.String bankaddress) {
		set("bankaddress", bankaddress);
		return (M)this;
	}

	public java.lang.String getBankaddress() {
		return get("bankaddress");
	}

	public M setAccountnumber(java.lang.Integer accountnumber) {
		set("accountnumber", accountnumber);
		return (M)this;
	}

	public java.lang.Integer getAccountnumber() {
		return get("accountnumber");
	}

	public M setAccountnumberremain(java.lang.Integer accountnumberremain) {
		set("accountnumberremain", accountnumberremain);
		return (M)this;
	}

	public java.lang.Integer getAccountnumberremain() {
		return get("accountnumberremain");
	}

}
