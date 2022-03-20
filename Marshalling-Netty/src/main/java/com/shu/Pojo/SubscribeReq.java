package com.shu.Pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Project_Name Marshalling-Netty
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/10/ 22:22
 * @Description 请求
 **/

@Data
public class SubscribeReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private int subReqID;
    private String userName;
    private String productName;
    private String phoneNumber;
    private String address;
    @Override
    public String toString() {
        return "SubscribeReq [subReqID=" + subReqID + ", userName=" + userName
                + ", productName=" + productName + ", phoneNumber="
                + phoneNumber + ", address=" + address + "]";
    }
}
