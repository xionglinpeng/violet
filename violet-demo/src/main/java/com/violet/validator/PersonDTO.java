package com.violet.validator;

import com.violet.validator.annotation.ExpUnique;
import com.violet.validator.annotation.ModifyUnique;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/2 上午9:04
 * @since 1.0.0
 */

@ModifyUnique(beanClass = ValidatorService.class,methodName = "existPersonByName",primary = "id")
public class PersonDTO {

    private Integer id;

    @ExpUnique(expression = "validatorService.existPersonByName",value = "")
    private String name;
}
