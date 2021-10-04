package com.imooc.miaosha.redis;

public class OrderKey extends BasePrefix{
    public OrderKey(int expiredSeconds, String prefix) {
        super(expiredSeconds, prefix);
    }
}
