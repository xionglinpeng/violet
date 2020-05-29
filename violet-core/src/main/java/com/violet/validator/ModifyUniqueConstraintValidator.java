package com.violet.validator;

import com.violet.validator.constraints.ModifyUnique;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/20 下午1:41
 * @since 1.0.0
 */
public class ModifyUniqueConstraintValidator implements ConstraintValidator<ModifyUnique,Object>, ApplicationContextAware {

    private ModifyUnique unique;

    private ApplicationContext applicationContext;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {





        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(ModifyUnique constraintAnnotation) {
        this.unique = constraintAnnotation;
    }
}
