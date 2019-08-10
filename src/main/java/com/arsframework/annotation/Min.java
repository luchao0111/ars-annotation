package com.arsframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数最小值校验表达式，适用于数字、枚举、日期、字符序列、数组、字典、集合、列表类型参数
 *
 * @author yongqiang.wu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER})
public @interface Min {
    /**
     * 默认异常信息
     */
    String DEFAULT_EXCEPTION_MESSAGE = "Argument '%s' must be greater than or equal to %d";

    /**
     * 获取参数最小值
     *
     * @return 最小值
     */
    long value();

    /**
     * 参数验证失败消息，格式化参数:参数名称、参数最小值
     *
     * @return 消息字符串
     */
    String message() default DEFAULT_EXCEPTION_MESSAGE;

    /**
     * 参数验证失败异常类型
     *
     * @return 异常类型
     */
    Class<? extends Throwable> exception() default IllegalArgumentException.class;
}
