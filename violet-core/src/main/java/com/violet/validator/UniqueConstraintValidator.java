package com.violet.validator;

import com.violet.validator.constraints.ExpUnique;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/20 上午9:34
 * @since 1.0.0
 */
public class UniqueConstraintValidator implements ConstraintValidator<ExpUnique, String> , ApplicationContextAware {

    private ExpUnique expUnique;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(ExpUnique expUnique) {
        this.expUnique = expUnique;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (!StringUtils.hasText(value)) {
//            return true;
//        }
//
//        //解析表达式
//        UniqueResolver uniqueResolver = new ExpUniqueResolver(applicationContext,expUnique);
//        UniqueAnnotationMetadata uniqueAnnotationMetadata = null;
//        try {
//            uniqueAnnotationMetadata = uniqueResolver.metadataResolver();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        //调用bean方法验证
//        try {
//            return uniqueAnnotationMetadata.invokeMethod(value);
//
//
//
//
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        return false;
    }
}
