package com.thinkingcao.store.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @desc:   授权异常
 * @author: cao_wencao
 * @date: 2020-09-22 17:59
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "unauthorized")
public class UnauthorizedException extends RuntimeException {
    private static final String DEFAULT_MSG = "unauthorized";

    private static final long serialVersionUID = 3885400551304383736L;

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super(DEFAULT_MSG);
    }
}