package com.violet.validator.constraints;

import com.violet.validator.NameFormatConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/5 下午2:48
 * @since 1.0.0
 */
@Documented
@Inherited
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameFormatConstraintValidator.class)
public @interface NameFormat {

    String format() default "";

    String message() default "数据不唯一";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
