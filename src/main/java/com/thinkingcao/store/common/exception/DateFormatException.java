package com.thinkingcao.store.common.exception;

/**
 * @desc:  时间格式化处理异常
 * @author: cao_wencao
 * @date: 2020-09-22 17:57
 */
public class DateFormatException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public DateFormatException(String message) {
        this.message = message;
    }

    public DateFormatException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
