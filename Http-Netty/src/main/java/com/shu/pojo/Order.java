package com.shu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 20:56
 * @Description 订单
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int count;
    private Customer customer;
    private Address address;
    private Shipping shipping;
    private Float countPrice;
}
