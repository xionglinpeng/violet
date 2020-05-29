package com.violet.validator.resolver;

import com.violet.validator.metadata.UniqueAnnotationMetadata;
import org.springframework.context.ApplicationContext;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/1 下午4:26
 * @since 1.0.0
 */
public abstract class UniqueResolver {

    protected ApplicationContext applicationContext;

    public UniqueResolver(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 唯一验证注解元数据解析
     * @return 注解元数据
     */
    protected abstract UniqueAnnotationMetadata metadataResolver() throws NoSuchMethodException;


}
