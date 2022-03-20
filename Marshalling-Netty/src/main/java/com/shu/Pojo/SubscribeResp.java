package com.shu.Pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Project_Name Marshalling-Netty
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/10/ 22:22
 * @Description 响应
 **/
@Data
public class SubscribeResp implements Serializable {
    private static final long serialVersionUID = 1L;
    private int subReqID;
    private int respCode;
    private String desc;
    @Override
    public String toString() {
        return "SubscribeResp [subReqID=" + subReqID + ", respCode=" + respCode
                + ", desc=" + desc + "]";
    }
}
