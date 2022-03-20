package com.shu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 20:41
 * @Description 地址
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street; // 街道
    private String city; // 城市
    private String province; //省份
    private String postcode; //邮编
    private String state; // 国家
}
