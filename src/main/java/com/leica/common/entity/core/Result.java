package com.leica.common.entity.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 统一API响应结果封装
 *
 * @author Leica
 * @date 2020/3/12 13:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    /**
     * 静态方法获取 Result 实例
     *
     * @param code 状态码 {@link HttpStatus}
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return {@link Result}
     */
    public static <T> Result<T> of(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    /**
     * 静态方法获取 Result 默认成功的实例
     *
     * @return 默认成功的实例
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS.value(), DEFAULT_SUCCESS_MESSAGE, null);
    }

    /**
     * 静态方法获取 Result 成功的实例
     *
     * @param data 返回对象
     * @param <T>  返回对象的类型
     * @return 成功的实例
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.value(), DEFAULT_SUCCESS_MESSAGE, data);
    }

    /**
     * 静态方法获取 Result 失败的实例
     *
     * @param message 错误信息
     * @return 失败的实例
     */
    public static Result<String> fail(String message) {
        return new Result<>(ResultCode.INTERNAL_SERVER_ERROR.value(), message, null);
    }

}
