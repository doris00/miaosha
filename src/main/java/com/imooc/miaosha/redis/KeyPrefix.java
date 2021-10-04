package com.imooc.miaosha.redis;

public interface KeyPrefix {
    public int expiredSeconds();
    public String getPrefix();
}
