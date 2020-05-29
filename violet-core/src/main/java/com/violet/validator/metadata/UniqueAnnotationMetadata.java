package com.violet.validator.metadata;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/20 上午9:49
 * @since 1.0.0
 */
public class UniqueAnnotationMetadata<V> {

    protected ApplicationContext context;

    protected String[] beanNames;

    protected Class<?> beanClass;

    protected Object beanInstance;

    protected String methodName;

    protected Method method;


    public UniqueAnnotationMetadata(ApplicationContext context, String beanName, String methodName, Class<V> valueClass) throws NoSuchMethodException {
        this.context = context;
        this.beanNames = new String[]{beanName};
        this.methodName = methodName;
        this.beanInstance = context.getBean(beanName);
        this.beanClass = beanInstance.getClass();
        this.method = this.beanClass.getMethod(methodName, valueClass);
    }

    public UniqueAnnotationMetadata(ApplicationContext context, Class<?> beanClass, String methodName, Class<V> valueClass) throws NoSuchMethodException {
        this.context = context;
        this.beanClass = beanClass;
        this.beanInstance = context.getBean(beanClass);
        this.beanNames = context.getBeanNamesForType(beanClass);
        this.methodName = methodName;
        this.method = this.beanClass.getMethod(methodName, valueClass);
    }


    public boolean invokeMethod(V args) throws InvocationTargetException, IllegalAccessException {
        Object result = this.method.invoke(this.beanInstance, args);
        if (ClassUtils.isAssignable(result.getClass(),Boolean.class)) {
            return (boolean)result;
        }
        throw new RuntimeException("");
    }



    public ApplicationContext getContext() {
        return context;
    }

    public String[] getBeanNames() {
        return beanNames;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public Object getBeanInstance() {
        return beanInstance;
    }

    public String getMethodName() {
        return methodName;
    }

    public Method getMethod() {
        return method;
    }
}
