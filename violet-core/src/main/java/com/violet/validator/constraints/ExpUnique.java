package com.violet.validator.constraints;

import com.violet.validator.UniqueConstraintValidator;
import org.springframework.core.annotation.AliasFor;

import javax.validation.Constraint;
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
@Constraint(validatedBy = UniqueConstraintValidator.class)
public @interface ExpUnique {

    @AliasFor("expression")
    String value();

    @AliasFor("value")
    String expression();

    String message() default "数据不唯一";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
