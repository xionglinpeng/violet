package com.violet.web.support.returns;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/22 下午5:03
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "violet.web.support.returns")
public class ReturnValueHandlerProperties {

    private String wrapperClass;

    private String pageWrapperClass;

    private List<String> ignorePaths;

    public String getWrapperClass() {
        return wrapperClass;
    }

    public void setWrapperClass(String wrapperClass) {
        this.wrapperClass = wrapperClass;
    }

    public String getPageWrapperClass() {
        return pageWrapperClass;
    }

    public void setPageWrapperClass(String pageWrapperClass) {
        this.pageWrapperClass = pageWrapperClass;
    }

    public List<String> getIgnorePaths() {
        return ignorePaths;
    }

    public void setIgnorePaths(List<String> ignorePaths) {
        this.ignorePaths = ignorePaths;
    }
}
