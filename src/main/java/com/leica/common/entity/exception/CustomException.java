package com.leica.common.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  自定义运行时异常 {@link RuntimeException}
 *
 * @author Leica
 * @date 2020/3/12 12:53
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,reason = "自定义异常")
public class CustomException extends RuntimeException {
    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

}
