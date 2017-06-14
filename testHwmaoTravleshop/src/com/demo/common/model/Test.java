/**
 * 
 */
package com.demo.common.model;

import com.demo.common.model.base.BaseTest;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Test extends BaseTest<Test> {
	
	public static final Test me=new Test().dao();
	
	public Page<Test> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from test order by id asc");
		
		
	}
}
