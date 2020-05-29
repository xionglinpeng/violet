package com.violet.web.support.returns.handler;

import java.lang.annotation.*;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/21 下午4:46
 * @since 1.0.0
 */
@Documented
@Inherited
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreWrapper {
}
