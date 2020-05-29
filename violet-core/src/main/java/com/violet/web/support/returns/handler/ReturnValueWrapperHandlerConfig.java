package com.violet.web.support.returns.handler;

import com.violet.web.support.returns.wrapper.BaseReturnWrapper;
import com.violet.web.support.returns.wrapper.BasePageReturnWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/22 下午5:12
 * @since 1.0.0
 */
public class ReturnValueWrapperHandlerConfig {

    private Class<? extends BaseReturnWrapper> wrapperClass;

    private Class<? extends BasePageReturnWrapper> pageWrapperClass;

    private List<String> ignorePaths = new ArrayList<>();

    public Class<? extends BaseReturnWrapper> getWrapperClass() {
        return wrapperClass;
    }

    public void setWrapperClass(Class<? extends BaseReturnWrapper> wrapperClass) {
        this.wrapperClass = wrapperClass;
    }

    public Class<? extends BasePageReturnWrapper> getPageWrapperClass() {
        return pageWrapperClass;
    }

    public void setPageWrapperClass(Class<? extends BasePageReturnWrapper> pageWrapperClass) {
        this.pageWrapperClass = pageWrapperClass;
    }

    public List<String> getIgnorePaths() {
        return ignorePaths;
    }

    public void setIgnorePaths(List<String> ignorePaths) {
        this.ignorePaths = ignorePaths;
    }
}
