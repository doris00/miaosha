package com.imooc.miaosha.redis;

public class GoodsKey extends BasePrefix{
    public GoodsKey(int expiredSeconds, String prefix) {
        super(expiredSeconds, prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey(60,"gl");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");
}
