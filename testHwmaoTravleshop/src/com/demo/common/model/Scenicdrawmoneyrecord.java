/**
 * 
 */
package com.demo.common.model;

import com.demo.common.model.base.BaseAgent;
import com.demo.common.model.base.BaseUser;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Scenicdrawmoneyrecord extends Model<Scenicdrawmoneyrecord> {
	
	public static final Scenicdrawmoneyrecord dao=new Scenicdrawmoneyrecord();
	 //分页查询用户
	 
	 
	 
	 public Page<Scenicdrawmoneyrecord> paginate(int pageNumber, int pageSize,String sqlstr) {
		 		return paginate(pageNumber, pageSize, "select *",sqlstr);
		}
	 
	 
	 
}
