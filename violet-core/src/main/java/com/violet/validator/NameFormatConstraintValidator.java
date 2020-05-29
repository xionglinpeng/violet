package com.violet.validator;

import com.violet.validator.constraints.NameFormat;
import org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Annotation;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/5 下午2:47
 * @since 1.0.0
 */
public class NameFormatConstraintValidator implements ConstraintValidator<NameFormat,String> {

    /**
     * 默认名称格式：字母、数字、中文、中下划线，最大长度32位。
     */
    private static final String DEFAULT_NAME_FORMAT = "[\\w\\u4e00-\\u9fa5\\\\-]{1,32}";

    private PatternValidator patternValidator = new PatternValidator();

    private HibernateValidatorProperties hibernateValidatorProperties;

    @Autowired
    public void setHibernateValidatorProperties(HibernateValidatorProperties hibernateValidatorProperties) {
        this.hibernateValidatorProperties = hibernateValidatorProperties;
    }

    @Override
    public void initialize(NameFormat nameFormat) {
        NamePattern namePattern = new NamePattern(nameFormat,hibernateValidatorProperties.getNameFormat());
        patternValidator.initialize(namePattern);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return patternValidator.isValid(value,context);
    }

    private static class NamePattern implements Pattern {

        private NameFormat nameFormat;

        private String configFormat;

        private NamePattern(NameFormat nameFormat,String configFormat) {
            this.nameFormat = nameFormat;
            this.configFormat = configFormat;
        }

        @Override
        public String regexp() {
            if (StringUtils.hasText(nameFormat.format())) {
                return nameFormat.format();
            }
            if (StringUtils.hasText(configFormat)) {
                return configFormat;
            }
            return DEFAULT_NAME_FORMAT;
        }

        @Override
        public Flag[] flags() {
            return new Flag[0];
        }

        @Override
        public String message() {
            return nameFormat.message();
        }

        @Override
        public Class<?>[] groups() {
            return nameFormat.groups();
        }

        @Override
        public Class<? extends Payload>[] payload() {
            return nameFormat.payload();
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return NameFormat.class;
        }
    }
}
