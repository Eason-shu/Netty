package com.shu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 20:58
 * @Description 顾客
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String userName;
    private String phone;
}
