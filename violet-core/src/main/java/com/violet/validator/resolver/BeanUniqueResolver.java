package com.violet.validator.resolver;

import com.violet.validator.metadata.UniqueAnnotationMetadata;
import com.violet.validator.constraints.BeanUnique;
import org.springframework.context.ApplicationContext;

/**
 * <p>唯一约束Bean解析器</p>
 *
 * @author xlp
 * @date 2020/4/1 下午4:27
 * @since 1.0.0
 */
public class BeanUniqueResolver extends UniqueResolver {

    private BeanUnique beanUnique;

    public BeanUniqueResolver(ApplicationContext applicationContext, BeanUnique beanUnique) {
        super(applicationContext);
        this.beanUnique = beanUnique;
    }

    @Override
    protected UniqueAnnotationMetadata metadataResolver() throws NoSuchMethodException {
        Class<?> beanClass = beanUnique.beanClass();
        String methodName = beanUnique.methodName();
        return new UniqueAnnotationMetadata<>(applicationContext,beanClass,methodName,String.class);
    }
}
