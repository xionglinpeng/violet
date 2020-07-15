package com.violet.web.support.returns.wrapper;

import com.violet.web.support.MessageStatus;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/21 下午4:56
 * @since 1.0.0
 */
public class DefaultReturnWrapper extends BaseReturnWrapper {

    private static final int CODE = MessageStatus.OK.value();

    private static final String MESSAGE = MessageStatus.OK.message();

    private Object code = CODE;

    private String message = MESSAGE;

    public DefaultReturnWrapper() {
    }

    public DefaultReturnWrapper(Object data) {
        super(data);
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
