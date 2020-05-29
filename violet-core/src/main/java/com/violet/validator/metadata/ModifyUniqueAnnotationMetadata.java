package com.violet.validator.metadata;

import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/20 下午1:53
 * @since 1.0.0
 */
public class ModifyUniqueAnnotationMetadata<K,V> extends UniqueAnnotationMetadata<V> {


    public ModifyUniqueAnnotationMetadata(ApplicationContext context, String beanName, String methodName, Class<K> keyClass, Class<V> valueClass) throws NoSuchMethodException {
        super(context, beanName, methodName, valueClass);
        this.method = this.beanClass.getMethod(methodName, keyClass, valueClass);
    }

    public ModifyUniqueAnnotationMetadata(ApplicationContext context, Class<?> beanClass, String methodName, Class<K> keyClass, Class<V> valueClass) throws NoSuchMethodException {
        super(context, beanClass, methodName, valueClass);
        this.method = this.beanClass.getMethod(methodName, keyClass, valueClass);
    }

    public boolean invokeMethod(K primary, V args) throws InvocationTargetException, IllegalAccessException {
        Object result = this.method.invoke(this.beanInstance, primary, args);
        if (ClassUtils.isAssignable(result.getClass(),Boolean.class)) {
            return (boolean)result;
        }
        throw new RuntimeException("");
    }
}
