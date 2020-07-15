package com.violet.web.support;

/**
 * <p>状态</p>
 *
 * @author xlp
 * @date 2020/7/15 下午2:11
 * @since 1.0.0
 */
public enum MessageStatus {

    /**
     *
     */
    OK(20000,"successfully"),
    NOT_FOUND(40004, "Not Found");

    private final int value;

    private final String message;

    MessageStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int value() {
        return value;
    }

    public String message() {
        return message;
    }
}
