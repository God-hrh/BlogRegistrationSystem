package com.example.demo1.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义方法注解
 * * @author Heerh
 * @version 1.0
 * @date 2020/1/28 0:25
 */
//创建一个类实现ConstraintValidator<emailcode,String>接口
public class EmailcodeValidator implements ConstraintValidator<emailcode,String> {

    @Autowired
    HttpServletRequest request;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String emailcode = (String) request.getSession().getAttribute("emailcode");
        return StringUtils.equals(s,emailcode);
    }
}
