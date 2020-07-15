package com.violet.web.support.returns.wrapper;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/21 下午4:55
 * @since 1.0.0
 */
public abstract class BaseReturnWrapper {

    private Object data;

    public BaseReturnWrapper() {
    }

    public BaseReturnWrapper(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
