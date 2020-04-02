package com.violet.validator.annotation;

import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/1 下午4:11
 * @since 1.0.0
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanUnique {

    Class<?> beanClass();

    String methodName();

    String message() default "数据不唯一";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
