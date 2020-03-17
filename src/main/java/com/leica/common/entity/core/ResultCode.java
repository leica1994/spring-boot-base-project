package com.leica.common.entity.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 响应码枚举
 *
 * @author Leica
 * @date 2020/3/13 11:32
 * @see HttpStatus
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * 失败
     */
    FAIL(400, "FAIL"),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * 接口不存在
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String reason;

    public int value() {
        return this.getCode();
    }
}
