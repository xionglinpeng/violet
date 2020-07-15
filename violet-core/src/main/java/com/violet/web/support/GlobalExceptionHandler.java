package com.violet.web.support;

import com.violet.exception.NotFoundResourceException;
import com.violet.web.support.returns.wrapper.BaseReturnWrapper;
import com.violet.web.support.returns.wrapper.DefaultReturnWrapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/7/15 下午2:08
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundResourceException.class)
    public BaseReturnWrapper notFoundResource(NotFoundResourceException e){
        DefaultReturnWrapper returnWrapper = new DefaultReturnWrapper();
        returnWrapper.setCode(MessageStatus.NOT_FOUND.value());
        returnWrapper.setMessage(e.getMessage());
        return returnWrapper;
    }
}
