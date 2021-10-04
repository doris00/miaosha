package com.imooc.miaosha.runner;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class TestJedisRunner {
    public static void main(String[] args) {
        Jedis jedis= new Jedis("10.8.15.240",6379); //使用ifconfig查看虚拟机centos的ip地址
        System.out.println(jedis.ping());
    }
}
