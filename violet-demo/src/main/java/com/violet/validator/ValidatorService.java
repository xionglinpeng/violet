package com.violet.validator;

import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/2 上午8:55
 * @since 1.0.0
 */
@Service("validatorService")
public class ValidatorService {

    public boolean existPersonByName(String name) {

        return true;
    }

    public boolean existPersonByName(Integer id, String name) {

        return true;
    }
}
