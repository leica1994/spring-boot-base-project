package com.leica.common.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 服务（业务）异常 如“ 账号或密码错误 ”
 *
 * @author Leica
 * @date 2020/3/13 15:27
 * @see WebMvcConfigurer
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "服务异常")
public class ServiceException extends RuntimeException {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }
}
