package com.ywc.ymall.constant;

/**
 * @author 嘟嘟~
 * @date 2020/4/14 21:19
 */
public class RedisCacheConstant {
    public static final String PRODUCT_CATEGORY_CACHE_KEY = "ymall:product:category:cache";

    //GULI:PRODUCT:INFO
    public static final String PRODUCT_INFO_CACHE_KEY = "yulishop:product:info:";

    public static final String USER_INFO_CACHE_KEY = "yulishop:user:info:";
    public static final long USER_INFO_TIMEOUT = 3L;//默认过期三天
    public static final String CART_TEMP = "ymall:cart:temp:";
    public static final String USER_CART = "ymall:cart:user:";
    public static final String TRADE_TOKEN = "ymall:trade:temptoken:";//+用户令牌

    public static final Long TRADE_TOKEN_TIME = 5L;//+用户令牌，以分钟为单位
    public static final String SEC_KILL = "ymall:sec:";
}
