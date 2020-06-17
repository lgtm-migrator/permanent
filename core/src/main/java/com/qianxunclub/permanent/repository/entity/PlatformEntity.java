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
@TableName("platform")
public class PlatformEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String platform;
    private String openId;
    private String token;
    private String expiresIn;
    private String refreshToken;
    private Timestamp updateAt;
    private Timestamp createAt;
}
