package com.violet.web.support.returns;

import com.violet.web.support.returns.handler.ReturnValueWrapperHandlerConfig;
import com.violet.web.support.returns.wrapper.BasePageReturnWrapper;
import com.violet.web.support.returns.wrapper.BaseReturnWrapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/22 下午5:02
 * @since 1.0.0
 */
//@Configuration
@EnableConfigurationProperties(ReturnValueHandlerProperties.class)
public class ReturnValueHandlerConfiguration {

    @Bean
    public RequestMappingHandlerAdapterAdvice requestMappingHandlerAdapterAdvice(
            ReturnValueHandlerProperties returnValueHandlerProperties) throws ClassNotFoundException {
        ReturnValueWrapperHandlerConfig wrapperHandlerConfig = new ReturnValueWrapperHandlerConfig();
        wrapperHandlerConfig.setWrapperClass(
                convertWrapperClass(returnValueHandlerProperties.getWrapperClass(), BaseReturnWrapper.class));
        wrapperHandlerConfig.setPageWrapperClass(
                convertWrapperClass(returnValueHandlerProperties.getPageWrapperClass(), BasePageReturnWrapper.class));
        wrapperHandlerConfig.setIgnorePaths(returnValueHandlerProperties.getIgnorePaths());
        return new RequestMappingHandlerAdapterAdvice(wrapperHandlerConfig);
    }


    @SuppressWarnings("unchecked")
    private <T> Class<T> convertWrapperClass(String stringWrapperClass, Class<T> rootWrapperClass) throws ClassNotFoundException {
        if (!StringUtils.hasText(stringWrapperClass)) {
            return null;
        }
        Class<?> wrapperClass = Class.forName(stringWrapperClass);
        if (!ClassUtils.isAssignable(rootWrapperClass, wrapperClass)) {
            throw new ClassCastException("");
        }
        return (Class<T>) wrapperClass;
    }

}
