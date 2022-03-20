package com.shu.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project_Name PrivateProctol-Netty
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/10/ 14:53
 * @Description 消息格式
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NettyMessage {
    private Head header; // 头部
    private Object body; // 消息正文
}
