package com.violet.exception;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/7/15 下午2:07
 * @since 1.0.0
 */
public class NotFoundResourceException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "not found resource";

    public NotFoundResourceException() {
        super(DEFAULT_MESSAGE);
    }

    public NotFoundResourceException(String message) {
        super(message);
    }

}
