package com.mao.tools.common;

/**
 * @author myseital
 * @date 2021/10/7 10:41
 */
public class SdkConstant {
    /**
     * 默认分页信息
     */
    public static final Integer DEFAULT_PAGE_NUM = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 10;


    public static final Integer ZERO_INT = 0;
    public static final Double ZERO_DOUBLE = 0.0;
    public static final Long ZERO_LONG = 0L;

    public static final Integer NEGATIVE_ONE_INT = -1;

    public static final String EMPTY_STRING = "";
    public static final String SUCCESS = "SUCCESS";
    public static final Integer SUCCESS_INT = 1;
    public static final String ERROR = "ERROR";
    public static final String SUCCESS_LOWERCASE = "success";
    public static final String TRUE_LOWERCASE = "true";
    public static final String FAILURE_LOWERCASE = "failure";
    public static final String ERROR_LOWERCASE = "error";
    /**
     * ,分割符
     */
    public static final String COMMA_SEPARATOR = ",";
    public static final String COLON = ":";

    /**
     * 保留几位小数
     */
    public static final Integer BIG_DECIMAL_SCALE = 3;
    /**
     * 百分比转换
     */
    public static final Integer PERCENTAGE = 100;

    /**
     * http错误码
     */
    public static final Integer HTTP_INTERNAL_ERROR = 500;

    public static final String ENCODING = "UTF-8";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String SOAP_ACTION = "SOAPAction";
    public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
    public static final String CONTENT_TYPE_XML = "text/xml; charset=UTF-8";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

}
