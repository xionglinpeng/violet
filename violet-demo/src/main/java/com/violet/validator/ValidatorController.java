package com.violet.validator;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/2 上午8:54
 * @since 1.0.0
 */
@Validated
@RestController
@RequestMapping("/validator")
public class ValidatorController {

    @PostMapping("/create")
    public boolean create(@Validated @RequestBody PersonDTO personDTO) {
        return true;
    }

    @PostMapping("/update")
    public boolean update(@Validated @RequestBody PersonDTO personDTO) {
        return true;
    }

}
