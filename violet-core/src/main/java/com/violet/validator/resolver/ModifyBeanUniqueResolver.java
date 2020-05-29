package com.violet.validator.resolver;

import com.violet.validator.constraints.ModifyUnique;
import com.violet.validator.metadata.UniqueAnnotationMetadata;
import org.springframework.context.ApplicationContext;

/**
 * <p>唯一约束Bean解析器</p>
 *
 * @author xlp
 * @date 2020/4/1 下午4:27
 * @since 1.0.0
 */
public class ModifyBeanUniqueResolver extends UniqueResolver {

    private ModifyUnique unique;

    public ModifyBeanUniqueResolver(ApplicationContext applicationContext, ModifyUnique unique) {
        super(applicationContext);
        this.unique = unique;
    }

    @Override
    protected UniqueAnnotationMetadata metadataResolver() throws NoSuchMethodException {
        Class<?> beanClass = unique.beanClass();
        String methodName = unique.methodName();
        return new UniqueAnnotationMetadata<>(applicationContext,beanClass,methodName,String.class);
    }
}
