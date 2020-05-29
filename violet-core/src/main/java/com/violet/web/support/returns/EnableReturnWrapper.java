package com.violet.web.support.returns;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/25 下午4:51
 * @since 1.0.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(ReturnValueHandlerConfiguration.class)
public @interface EnableReturnWrapper {
}
