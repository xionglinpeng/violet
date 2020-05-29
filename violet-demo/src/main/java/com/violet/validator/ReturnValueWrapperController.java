package com.violet.validator;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/25 下午5:12
 * @since 1.0.0
 */
@Validated
@RestController
@RequestMapping("/return/wrapper")
public class ReturnValueWrapperController {

    @GetMapping
    public String hello(){
        return "hello world!";
    }
}
