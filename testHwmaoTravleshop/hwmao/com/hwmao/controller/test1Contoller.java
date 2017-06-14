/**
 * 
 */
package com.hwmao.controller;





import com.hwmao.nio.HelloPacket;
import com.hwmao.nio.HelloServerStarter;
import com.jfinal.core.Controller;
import com.talent.aio.common.Aio;


/**
 * @author Administrator
 *
 */
public class test1Contoller extends Controller{
	
	
	public void aio(){
		HelloPacket hello = new HelloPacket();
		byte arr[] = {104, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100}	;
		hello.setBody(arr);
		Aio.sendToUser(HelloServerStarter.serverGroupContext, getPara(), hello);
		renderJson();
	}
	
}

