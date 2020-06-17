package com.qianxunclub.permanent.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author zhangbin
 */

@Getter
@AllArgsConstructor
public enum CodeConstants {

    /**
     *
     */
    SUCCESS("0", "success", HttpStatus.OK),
    FAIL("1", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * 权限相关
     */
    USER_NOT_LOGIN("200", "user_not_login", HttpStatus.FORBIDDEN),
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String message;
    /**
     *
     */
    private final HttpStatus httpStatus;


}
