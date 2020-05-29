package com.violet.validator;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/5 下午3:54
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "hibernate.validator")
public class HibernateValidatorProperties {

    private String codeFormat;

    private String nameFormat;

    public String getCodeFormat() {
        return codeFormat;
    }

    public void setCodeFormat(String codeFormat) {
        this.codeFormat = codeFormat;
    }

    public String getNameFormat() {
        return nameFormat;
    }

    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }
}
