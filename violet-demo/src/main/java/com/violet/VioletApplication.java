package com.violet;

import com.violet.web.support.returns.EnableReturnWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/2 上午9:26
 * @since 1.0.0
 */
@SpringBootApplication
@EnableReturnWrapper
public class VioletApplication {

    public static void main(String[] args) {
        SpringApplication.run(VioletApplication.class,args);
    }
}
