package com.shu.Protocol;

import org.msgpack.annotation.Message;


/**
 * @Author shu
 * @Date: 2022/03/03/ 20:05
 * @Description 自定义消息实体类 自
 * 定义消息格式 [指令][时间戳][username][头像][消息内容]
 **/
@Message
public class IMMessage{
	private String addr;		//IP地址及端口
	private String cmd;		//命令类型[LOGIN]或者[SYSTEM]或者[LOGOUT]
	private long time;		//命令发送时间
	private int online;		//当前在线人数
	private String sender;  //发送人
	private String headPic;
	private String receiver;	//接收人
	private String content;	//消息内容

	public IMMessage(){}

	public IMMessage(String cmd,long time,int online,String content){
		this.cmd = cmd;
		this.time = time;
		this.online = online;
		this.content = content;
	}

	public IMMessage(String cmd,long time,String sender){
		this.cmd = cmd;
		this.time = time;
		this.sender = sender;
	}
	public IMMessage(String cmd,long time,String sender,String content){
		this.cmd = cmd;
		this.time = time;
		this.sender = sender;
		this.content = content;
	}

	public IMMessage(String cmd,long time,String sender,String content,String headPic){
		this.cmd = cmd;
		this.time = time;
		this.sender = sender;
		this.content = content;
		this.headPic = headPic;
	}
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}


	@Override
	public String toString() {
		return "IMMessage{" +
				"addr='" + addr + '\'' +
				", cmd='" + cmd + '\'' +
				", time=" + time +
				", online=" + online +
				", sender='" + sender + '\'' +
				", headPic='" + headPic + '\'' +
				", receiver='" + receiver + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
