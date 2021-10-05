package com.imooc.miaosha.service;


import com.imooc.miaosha.domain.OrderInfo;
import com.imooc.miaosha.domain.SeckillUser;
import com.imooc.miaosha.vo.GoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MiaoshaService {

    @Resource
    GoodsService goodsService;

    @Resource
    OrderService orderService;


    public OrderInfo miaosha(SeckillUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        return orderService.createOrder(user, goods);
    }
}
