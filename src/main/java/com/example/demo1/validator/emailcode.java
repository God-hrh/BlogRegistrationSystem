package com.example.demo1.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/1/28 0:24
 */
@Documented
@Constraint(validatedBy = EmailcodeValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface emailcode {
        String message () default  "验证码验证失败！";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

}
