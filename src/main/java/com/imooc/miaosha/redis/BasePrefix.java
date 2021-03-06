package com.imooc.miaosha.redis;




public abstract class BasePrefix implements KeyPrefix{

    private int expiredSeconds;

    private String prefix;

    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix(int expiredSeconds, String prefix) {
        this.expiredSeconds = expiredSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expiredSeconds() {//默认0，代表永不过期
        return expiredSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
