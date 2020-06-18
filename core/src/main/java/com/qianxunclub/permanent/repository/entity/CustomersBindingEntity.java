package com.qianxunclub.permanent.repository.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhangbin
 */
@Data
@TableName("customers_binding")
public class CustomersBindingEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String platform;
    private Long customersId;
    private Long platformId;
}
