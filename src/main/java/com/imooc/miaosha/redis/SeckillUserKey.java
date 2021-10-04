package com.imooc.miaosha.redis;

public class SeckillUserKey extends BasePrefix{

    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public static SeckillUserKey token = new SeckillUserKey(TOKEN_EXPIRE, "tk");

    public SeckillUserKey(int expiredSeconds, String prefix) {
        super(expiredSeconds, prefix);
    }
}