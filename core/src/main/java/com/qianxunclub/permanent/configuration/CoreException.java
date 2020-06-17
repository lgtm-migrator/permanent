package com.qianxunclub.permanent.configuration;

import com.qianxunclub.permanent.constants.CodeConstants;
import com.qianxunclub.permanent.model.Result;
import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@Slf4j
@ControllerAdvice
public class CoreException extends Exception {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handle(HttpServletRequest request, Exception exception) throws Exception {
        return handleToResult(request, exception);
    }

    private ResponseEntity handleToResult(HttpServletRequest request, Exception exception) {
        Result result;
        if (exception instanceof CoreException) {
            CoreException coreException = (CoreException) exception;
            result = new Result(coreException.code, coreException.message);
        } else {
            result = new Result(CodeConstants.FAIL);
        }
        log.error(result.toString(), exception);
        return new ResponseEntity<>(result, this.httpStatus);
    }

    private String code;
    private String message;
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public CoreException() {
    }

    public CoreException(CodeConstants codeConstants) {
        this.code = codeConstants.getCode();
        this.message = codeConstants.getMessage();
        this.httpStatus = codeConstants.getHttpStatus();
    }

    public static CoreException of(CodeConstants codeConstants) {
        return new CoreException(codeConstants);
    }

    @Override
    public String getMessage() {
        return String.format("code: %s, message: %s", this.code, this.message);
    }
}
