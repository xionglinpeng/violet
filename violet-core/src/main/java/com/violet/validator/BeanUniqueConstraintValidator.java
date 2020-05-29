package com.violet.validator;

import com.violet.validator.constraints.BeanUnique;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/20 下午1:34
 * @since 1.0.0
 */
public class BeanUniqueConstraintValidator implements ConstraintValidator<BeanUnique, String>, ApplicationContextAware {

    private BeanUnique unique;

    private ApplicationContext applicationContext;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //解析表达式
//        UniqueResolver uniqueResolver = new BeanUniqueResolver(applicationContext,unique);
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(BeanUnique constraintAnnotation) {
        this.unique = constraintAnnotation;
    }
}
