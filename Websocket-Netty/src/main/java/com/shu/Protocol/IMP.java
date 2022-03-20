package com.shu.Protocol;


/**
 * @Author shu
 * @Date: 2022/03/03/ 20:05
 * @Description 自定义IM协议，Instant Messaging Protocol即时通信协议
 *  *
 **/
public enum IMP {
	/** 系统消息 */
	SYSTEM("SYSTEM"),
	/** 登录指令 */
	LOGIN("LOGIN"),
	/** 登出指令 */
	LOGOUT("LOGOUT"),
	/** 聊天消息 */
	CHAT("CHAT"),
	/** 送鲜花 */
	FLOWER("FLOWER");


	private String name;


	public static boolean isIMP(String content){
		return content.matches("^\\[(SYSTEM|LOGIN|LOGOUT|CHAT|FLOWER)\\]");
	}
	
	IMP(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return this.name;
	}
	
}
