package com.violet.web.support.returns;

import com.violet.web.support.returns.handler.HandlerMethodReturnValueWrapperHandler;
import com.violet.web.support.returns.handler.ReturnValueWrapperHandlerConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/22 下午4:56
 * @since 1.0.0
 */
public class RequestMappingHandlerAdapterAdvice implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private ReturnValueWrapperHandlerConfig returnValueWrapperHandlerConfig;

    public RequestMappingHandlerAdapterAdvice() {
    }

    public RequestMappingHandlerAdapterAdvice(ReturnValueWrapperHandlerConfig returnValueWrapperHandlerConfig) {
        this.returnValueWrapperHandlerConfig = returnValueWrapperHandlerConfig;
    }

    @Override
    public void afterPropertiesSet() {
        returnValueHandlerAdvice();
    }

    private void returnValueHandlerAdvice() {
        List<HandlerMethodReturnValueHandler> unmodifiableReturnValueHandlers =
                requestMappingHandlerAdapter.getReturnValueHandlers();
        Objects.requireNonNull(unmodifiableReturnValueHandlers);
        List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<>();
        for (HandlerMethodReturnValueHandler returnValueHandler : unmodifiableReturnValueHandlers) {
            if (ClassUtils.isAssignable(returnValueHandler.getClass(), RequestResponseBodyMethodProcessor.class)) {
                returnValueHandlers.add(new HandlerMethodReturnValueWrapperHandler(
                        (RequestResponseBodyMethodProcessor) returnValueHandler, returnValueWrapperHandlerConfig));
                continue;
            }
            returnValueHandlers.add(returnValueHandler);
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(returnValueHandlers);
    }
}
