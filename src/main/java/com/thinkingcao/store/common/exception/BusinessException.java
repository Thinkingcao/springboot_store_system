package com.thinkingcao.store.common.exception;

/**
 * @desc:   业务异常
 * @author: cao_wencao
 * @date: 2020-09-22 17:59
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected final String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

