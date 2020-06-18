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
     * 通用
     */
    NOT_SUPPORTED("200", "Not Supported", HttpStatus.INTERNAL_SERVER_ERROR),
    PARAMETER_INVALID("201", "Param invalid", HttpStatus.BAD_REQUEST),

    /**
     * 权限相关
     */
    USER_NOT_LOGIN("300", "User Not Login", HttpStatus.FORBIDDEN),
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private String message;
    /**
     *
     */
    private final HttpStatus httpStatus;

    public CodeConstants setMessage(String message) {
        this.message = message;
        return this;
    }
}
