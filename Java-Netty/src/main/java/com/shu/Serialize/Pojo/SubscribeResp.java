package com.shu.Serialize.Pojo;

import java.io.Serializable;

/**
 * @Author shu
 * @Date: 2022/03/05/ 20:05
 * @Description
 **/
public class SubscribeResp implements Serializable {

    private int subRespId;
    private int respCode;
    private String desc;

    public SubscribeResp(int subRespId, int respCode, String desc) {
        this.subRespId = subRespId;
        this.respCode = respCode;
        this.desc = desc;
    }

    public SubscribeResp() {
    }


    public int getSubRespId() {
        return subRespId;
    }

    public void setSubRespId(int subRespId) {
        this.subRespId = subRespId;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "SubscribeResp{" +
                "subRespId=" + subRespId +
                ", respCode=" + respCode +
                ", desc='" + desc + '\'' +
                '}';
    }
}
