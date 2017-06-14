/**
 * 
 */
package com.demo.common.model;

import java.awt.List;

import com.demo.common.model.base.BaseAgent;
import com.demo.common.model.base.BaseUser;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Order extends Model<Order> {
	
	public static final Order dao=new Order();
	
 
	 
	 //分页查询订单
	 public Page<Order> paginate(int pageNumber, int pageSize,String sqlstr) {
		 		return paginate(pageNumber, pageSize, "select *",sqlstr);
		}
	 

	
	 
}
