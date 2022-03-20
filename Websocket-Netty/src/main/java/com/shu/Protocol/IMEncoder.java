package com.shu.Protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import org.msgpack.MessagePack;

/**
 * @Author shu
 * @Date: 2022/03/03/ 20:05
 * @Description 自定义IM协议的编码器
 **/
@Slf4j
public class IMEncoder extends MessageToByteEncoder<IMMessage> {

	/**
	 *
	 * 编码
	 * @param ctx
	 * @param msg
	 * @param out
	 * @throws Exception
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, IMMessage msg, ByteBuf out)
			throws Exception {
		out.writeBytes(new MessagePack().write(msg));
	}

	public String encode(IMMessage msg){
		if(null == msg){ return ""; }
		String prex = "[" + msg.getCmd() + "]" + "[" + msg.getTime() + "]";
		if(IMP.LOGIN.getName().equals(msg.getCmd()) ||
		   IMP.FLOWER.getName().equals(msg.getCmd())){
			prex += ("[" + msg.getSender() + "]");
		}else if(IMP.CHAT.getName().equals(msg.getCmd()) ){
			prex += ("[" + msg.getSender() + "]["+msg.getHeadPic()+"]");
		}
		else if(IMP.SYSTEM.getName().equals(msg.getCmd())){
			prex += ("[" + msg.getOnline() + "]");
		}
		if(!(null == msg.getContent() || "".equals(msg.getContent()))){
			prex += (" - " + msg.getContent());
		}
		log.info("编码消息"+prex);
		return prex;
	}

}
