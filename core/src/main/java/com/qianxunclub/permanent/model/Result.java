package com.qianxunclub.permanent.model;

import com.qianxunclub.permanent.constants.CodeConstants;
import com.qianxunclub.permanent.utils.JsonUtil;
import lombok.Getter;

/**
 * @author zhangbin
 */
@Getter
public class Result<T> {

    private String code;
    private String message;
    private T data;

    public Result() {
        this.code = CodeConstants.SUCCESS.getCode();
        this.message = CodeConstants.SUCCESS.getMessage();
    }

    public Result(CodeConstants codeConstants) {
        this.code = codeConstants.getCode();
        this.message = codeConstants.getMessage();
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result fail() {
        return new Result(CodeConstants.FAIL);
    }

    public static Result success() {
        return new Result(CodeConstants.SUCCESS);
    }

    public static <T> Result success(T data) {
        Result<T> result = new Result(CodeConstants.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result fail(CodeConstants codeConstants) {
        return new Result(codeConstants);
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return String.format("code: %s, message: %s, data: %s", this.code, this.message,
            JsonUtil.getGson().toJson(this.data));
    }
}
