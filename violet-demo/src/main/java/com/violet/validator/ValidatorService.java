package com.violet.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/2 上午8:55
 * @since 1.0.0
 */
@Service("validatorService")
public class ValidatorService {

    private static final Log log = LogFactory.getLog(ValidatorService.class);

    public boolean doesExistName(String name) {
        log.info("Validate name. name = "+name);
        return true;
    }

    public boolean doesExistName(Integer id, String name) {
        log.info("Validate name. id = "+ id +", name = "+name);
        return true;
    }
}
