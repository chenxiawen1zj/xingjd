package com.hwmao.nio;

import java.io.IOException;



import com.jfinal.plugin.IPlugin;
import com.talent.aio.server.AioServer;
import com.talent.aio.server.ServerGroupContext;
import com.talent.aio.server.intf.ServerAioHandler;
import com.talent.aio.server.intf.ServerAioListener;

/**
 * 
 * @author tanyaowu 
 * 2017年4月4日 下午12:22:58
 */
public class HelloServerStarter implements IPlugin
{
/*	//handler, 包括编码、解码、消息处理
	public static ServerAioHandler<Object, HelloPacket, Object> aioHandler = new HelloServerAioHandler();
	
	//事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
	public static ServerAioListener<Object, HelloPacket, Object> aioListener = null;
	
	//一组连接共用的上下文对象
	public static ServerGroupContext<Object, HelloPacket, Object> serverGroupContext = new ServerGroupContext<>(aioHandler, aioListener);
	
	//aioServer对象
	public static AioServer<Object, HelloPacket, Object> aioServer = new AioServer<>(serverGroupContext);
	
	//有时候需要绑定ip，不需要则null
	public static String serverIp = null;
	
	//监听的端口
	public static int serverPort = Const.PORT;

	*//**
	 * 启动程序入口
	 *//*
	public static void main(String[] args) throws IOException
	{
		aioServer.start(serverIp, serverPort);
	}

	 (non-Javadoc)
	 * @see com.jfinal.plugin.IPlugin#start()
	 
	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return false;
	}

	 (non-Javadoc)
	 * @see com.jfinal.plugin.IPlugin#stop()
	 
	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}*/
	public static ServerGroupContext<Object, HelloPacket, Object> serverGroupContext = null;
	static AioServer<Object, HelloPacket, Object> aioServer = null; // 可以为空
	static ServerAioHandler<Object, HelloPacket, Object> aioHandler = null;
	static ServerAioListener<Object, HelloPacket, Object> aioListener = null;
	static String serverIp = null;
	static int serverPort = Const.PORT;

	public static void main(String[] args) throws IOException {
		aioHandler = new HelloServerAioHandler();
		aioListener = null; // 可以为空
		serverGroupContext = new ServerGroupContext<>(aioHandler, aioListener);
		aioServer = new AioServer<>(serverGroupContext);
		aioServer.start(serverIp, serverPort);
	}

	@Override
	public boolean start() {
		aioHandler = new HelloServerAioHandler();
		aioListener = null; // 可以为空
		serverGroupContext = new ServerGroupContext<>(aioHandler, aioListener);
		aioServer = new AioServer<>(serverGroupContext);
		try {
			aioServer.start(serverIp, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean stop() {
		return aioServer.stop();
	}
}