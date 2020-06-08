package com.qianxunclub.permanent.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oauth")
public class OauthEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String platform;
    private String openId;
    private String token;
    private String expiresIn;
    private String refreshToken;
}
