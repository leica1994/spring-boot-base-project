package com.leica.common.config;

import com.leica.common.entity.core.Result;
import com.leica.common.entity.exception.CustomException;
import com.leica.common.entity.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

import static com.leica.common.entity.core.Result.fail;

/**
 * 全局异常处理器
 *
 * @author Leica
 * @date 2020/3/12 12:58
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理 服务异常
     *
     * @param ex {@link ServiceException}
     * @return {@link Result}
     */
    @ExceptionHandler(ServiceException.class)
    public Result<String> handServiceException(ServiceException ex) {
        writeExceptionToLog(ex);
        return fail(ex.getMessage());
    }

    /**
     * 处理 自定义异常
     *
     * @param ex {@link CustomException}
     * @return {@link Result}
     */
    @ExceptionHandler(CustomException.class)
    public Result<String> handCustomException(CustomException ex) {
        writeExceptionToLog(ex);
        return fail(ex.getMessage());
    }

    private void writeExceptionToLog(Exception ex) {
        log.error("出现异常:", ex);
    }

}
