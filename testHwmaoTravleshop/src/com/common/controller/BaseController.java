/**
 * 
 */
package com.common.controller;

import com.jfinal.core.Controller;

/**
 * @author Administrator
 *
 */
public class BaseController extends Controller {
	
	protected void ajaxDone(int statusCode, String message) {
	    setAttr("statusCode", statusCode);
	    setAttr("message", message);
	    // 跳转路径
	    String forwardUrl = getPara("forwardUrl");
	    if (forwardUrl == null || forwardUrl.equals("")) {
	      forwardUrl = getRequest().getRequestURL().toString();
	    }
	    setAttr("forwardUrl", forwardUrl);
	    setAttr("callbackType", getPara("callbackType"));
	  }
	  protected void ajaxDoneSuccess(String message) {
	    ajaxDone(200, message);
	  }
	  protected void ajaxDoneInfo(String message) {
	    ajaxDone(201, message);
	  }
	  protected void ajaxDoneSuccess(String message, String forwarUrl) {
	    ajaxDone(200, message);
	  }
	  protected void ajaxDoneError(String message) {
	    ajaxDone(300, message);
	  }
	  protected void ajaxDoneError(String message, String forwarUrl) {
	    ajaxDone(300, message);
	  }
}
