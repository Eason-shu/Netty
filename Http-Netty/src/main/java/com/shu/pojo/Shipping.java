package com.shu.pojo;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 20:52
 * @Description 寄送方式
 **/
public enum Shipping {

    A(0, "普通邮寄"),
    B(100, "宅急送"),
    C(-100, "国际快递"),
    D(200, "国内快递");

    private int code;
    private String desc;

    Shipping(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
