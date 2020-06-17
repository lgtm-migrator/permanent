package com.qianxunclub.permanent.model;

import com.qianxunclub.permanent.repository.entity.CustomersEntity;
import com.qianxunclub.permanent.repository.entity.PlatformEntity;
import java.sql.Timestamp;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangbin
 */
@Data
public class CustomersInfo {

    private Long id;
    private String username;
    private String phone;
    private String email;
    private String gender;
    private String nickname;
    private String avatarUrl;
    private String platform;
    private String openId;
    private Timestamp createAt;
    private Timestamp updateAt;

    public static CustomersInfo toDTO(CustomersEntity customersEntity,
        PlatformEntity platformEntity) {
        CustomersInfo customersInfo = new CustomersInfo();
        BeanUtils.copyProperties(platformEntity, customersInfo);
        BeanUtils.copyProperties(customersEntity, customersInfo);
        return customersInfo;
    }


}
