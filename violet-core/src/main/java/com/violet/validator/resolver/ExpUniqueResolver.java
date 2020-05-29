package com.violet.validator.resolver;

import com.violet.validator.metadata.UniqueAnnotationMetadata;
import com.violet.validator.constraints.ExpUnique;
import org.springframework.context.ApplicationContext;

/**
 * <p>唯一约束表达式解析器</p>
 *
 * @author xlp
 * @date 2020/4/1 下午4:27
 * @since 1.0.0
 */
public class ExpUniqueResolver extends UniqueResolver {

    private ExpUnique expUnique;


    public ExpUniqueResolver(ApplicationContext applicationContext,ExpUnique expUnique) {
        super(applicationContext);
        this.expUnique = expUnique;
    }

    @Override
    public UniqueAnnotationMetadata metadataResolver() throws NoSuchMethodException {
        String expression = expUnique.expression();
        String[] expr = expression.split("\\.");
        String beanName = expr[0];
        String methodName = expr[1];
        return new UniqueAnnotationMetadata<>(applicationContext,beanName,methodName,String.class);
    }
}
