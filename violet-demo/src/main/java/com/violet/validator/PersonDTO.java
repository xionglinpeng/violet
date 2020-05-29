package com.violet.validator;

import com.violet.validator.constraints.BeanUnique;
import com.violet.validator.constraints.ExpUnique;
import com.violet.validator.constraints.ModifyUnique;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

import javax.validation.constraints.NotEmpty;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/2 上午9:04
 * @since 1.0.0
 */
@Data
@CommonsLog
@ModifyUnique(beanClass = ValidatorService.class,methodName = "existPersonByName",primary = "id")
public class PersonDTO {

    private Integer id;

    @NotEmpty(message = "名称不能为空")
    @ExpUnique(expression = "validatorService.doesExistName",value = "",message = "名称不唯一")
    @BeanUnique(beanClass = ValidatorService.class,methodName = "doesExistName")
    private String name;
}
