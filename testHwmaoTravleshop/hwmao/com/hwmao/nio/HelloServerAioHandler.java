package com.hwmao.nio;

import com.talent.aio.common.Aio;
import com.talent.aio.common.ChannelContext;
import com.talent.aio.server.intf.ServerAioHandler;

/**
 * 
 * @author tanyaowu 
 *
 */
public class HelloServerAioHandler extends HelloAbsAioHandler implements ServerAioHandler<Object, HelloPacket, Object>
{
	/** 
	 * 处理消息
	 */
	@Override
	public Object handler(HelloPacket packet, ChannelContext<Object, HelloPacket, Object> channelContext) throws Exception
	{
		byte[] body = packet.getBody();
		if (body != null)
		{
			String str = new String(body, HelloPacket.CHARSET);
			System.out.println("收到消息：" + str);
			// 绑定长连接
			Aio.bindUser(channelContext, "1234");
			HelloPacket resppacket = new HelloPacket();
			resppacket.setBody(("收到了你的消息，你的消息是:" + str).getBytes(HelloPacket.CHARSET));
			Aio.send(channelContext, resppacket);
		}
		return null;
	}
}
