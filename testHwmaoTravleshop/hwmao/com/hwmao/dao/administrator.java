/**
 * 
 */
package com.hwmao.dao;

import java.io.UnsupportedEncodingException;

import com.demo.common.model.base.BaseAdministrator;

import com.jfinal.plugin.activerecord.Page;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class administrator extends BaseAdministrator<administrator> {
	
	public static final administrator dao=new administrator();
	
	// 返回首页list
	public Page<administrator> findfirstpagedate(int page,int pageSize,String sort,String order) {
			
			
		  	String hql =" FROM `t_administrator` order by ?  ?";
		  	Object[] values = {sort,order}; 
	        Page<administrator> page1=paginate(page, pageSize, "SELECT * ", hql, values);
	       
			//对nickname 解码
			
	
	        return page1;
	}
	 
	 
}
