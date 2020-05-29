package com.violet.validator.constraints;

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
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModifyUnique {

    Class<?> beanClass();

    String methodName();

    String primary();

    String message() default "数据不唯一";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
