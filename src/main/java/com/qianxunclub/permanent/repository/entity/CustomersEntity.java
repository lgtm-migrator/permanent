package com.qianxunclub.permanent.repository.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Timestamp;
import lombok.Data;

/**
 * @author zhangbin
 */
@Data
@TableName("customers")
public class CustomersEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String gender;
    private String nickname;
    private String avatarUrl;
    private Timestamp createAt;
    private Timestamp updateAt;
}
