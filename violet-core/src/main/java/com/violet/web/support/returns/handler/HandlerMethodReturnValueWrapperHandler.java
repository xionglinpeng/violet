package com.violet.web.support.returns.handler;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.violet.web.support.returns.wrapper.*;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/21 下午4:42
 * @since 1.0.0
 */
public class HandlerMethodReturnValueWrapperHandler implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler delegate;

    private Class<? extends BaseReturnWrapper> wrapperClass;

    private Class<? extends BasePageReturnWrapper> pageWrapperClass;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private List<String> ignorePaths = new ArrayList<>();

    private static boolean JpaPageClass;

    private static boolean baomidouPageClass;

    static {
        JpaPageClass = isPresent("org.springframework.data.domain.Page");
        baomidouPageClass = isPresent("com.baomidou.mybatisplus.core.metadata.IPage");
    }

    public HandlerMethodReturnValueWrapperHandler(RequestResponseBodyMethodProcessor delegate, ReturnValueWrapperHandlerConfig wrapperHandlerConfig) {
        this.delegate = delegate;
        if (Objects.nonNull(wrapperHandlerConfig)) {
            this.wrapperClass = wrapperHandlerConfig.getWrapperClass();
            this.pageWrapperClass = wrapperHandlerConfig.getPageWrapperClass();
            if (Objects.nonNull(wrapperHandlerConfig.getIgnorePaths()) && !wrapperHandlerConfig.getIgnorePaths().isEmpty()) {
                this.ignorePaths.addAll(wrapperHandlerConfig.getIgnorePaths());
            }
        }
    }

    private static boolean isPresent(String className) {
        try {
            Class.forName(className, false, HandlerMethodReturnValueWrapperHandler.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return delegate.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        //ignore wrapper
        if (!ignoreWrapper(returnValue, methodParameter, modelAndViewContainer, nativeWebRequest)) {
            //wrapper
            returnValue = returnValueWrapper(returnValue, methodParameter);
        }
        delegate.handleReturnValue(returnValue, methodParameter, modelAndViewContainer, nativeWebRequest);
    }

    protected Object returnValueWrapper(Object returnValue, MethodParameter methodParameter) throws Exception {
        Object value = returnValue;
        if (Objects.nonNull(value)) {
            if (JpaPageClass && ClassUtils.isAssignable(Page.class, value.getClass())) {
                //JPA paging object
                value = returnPageValueWrapper(returnValue, JpaPageReturnWrapper::new);
            }
            if (baomidouPageClass && ClassUtils.isAssignable(IPage.class, value.getClass())) {
                //Baomidou paging object
                value = returnPageValueWrapper(returnValue, BaomidouPageReturnWrapper::new);
            }
        }
        if (Objects.isNull(wrapperClass)) {
            value = new DefaultReturnWrapper(value);
        } else {
            value = wrapperClassInstance(wrapperClass, value);
        }
        return value;
    }

    private Object returnPageValueWrapper(Object returnValue, Function<Object, Object> wrapper) throws Exception {
        if (Objects.isNull(pageWrapperClass)) {
            return wrapper.apply(returnValue);
        } else {
            return wrapperClassInstance(pageWrapperClass, returnValue);
        }
    }

    /**
     * 如果返回值满足以下下条件，将会忽略包装：
     * <ul>
     *     <li>返回值类型是{@code ReturnWrapper.class}。</li>
     *     <li>请求地址匹配忽略路径的Ant模式。</li>
     *     <li>返回值目标方法标注了{@link IgnoreWrapper}注解。</li>
     * </ul>
     *
     * @param returnValue     返回值
     * @param methodParameter 返回值目标方法
     * @return 忽略包装条件为true，将不会包装，为false，将包装。
     */
    protected boolean ignoreWrapper(Object returnValue, MethodParameter methodParameter,
                                    ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Objects.requireNonNull(request);
        for (String ignorePath : ignorePaths) {
            return pathMatcher.match(ignorePath, request.getServletPath());
        }
        return (Objects.nonNull(returnValue) && ClassUtils.isAssignable(returnValue.getClass(), BaseReturnWrapper.class)) ||
                methodParameter.hasMethodAnnotation(IgnoreWrapper.class);
    }


    private Object wrapperClassInstance(Class<?> wrapperClass, Object returnValue)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return wrapperClass.getConstructor(new Class[]{Object.class}).newInstance(returnValue);
    }

    public HandlerMethodReturnValueHandler getDelegate() {
        return delegate;
    }

    public void setDelegate(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

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

    public void setIgnorePaths(List<String> ignorePaths) {
        this.ignorePaths = ignorePaths;
    }

    public List<String> getIgnorePaths() {
        return ignorePaths;
    }
}
