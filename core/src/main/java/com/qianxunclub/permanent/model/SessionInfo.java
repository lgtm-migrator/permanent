package com.qianxunclub.permanent.model;

import lombok.Data;

@Data
public class SessionInfo {
    private Long customersId;
    private String username;
    private String phone;
    private String email;
    private String gender;
    private String nickname;
    private String platform;
    private String openId;

}
