package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.OrderDao;
import com.imooc.miaosha.domain.OrderInfo;
import com.imooc.miaosha.domain.SeckillOrder;
import com.imooc.miaosha.domain.SeckillUser;
import com.imooc.miaosha.vo.GoodsVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderService {

    @Resource
    OrderDao orderDao;

    public SeckillOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
    }

    @Transactional
    public OrderInfo createOrder(SeckillUser user, GoodsVo goods) {
        OrderInfo orderInfo = OrderInfo.builder()
                .createDate(new Date())
                .deliveryAddrId(0L)
                .goodsCount(1)
                .goodsId(goods.getId())
                .goodsName(goods.getGoodsName())
                .goodsPrice(goods.getSeckillPrice())
                .orderChannel(1)
                .status(0)
                .userId(user.getId())
                .build();
        long orderId = orderDao.insert(orderInfo);

        SeckillOrder seckillOrder = SeckillOrder.builder()
                .orderId(orderId)
                .goodsId(goods.getId())
                .userId(user.getId())
                .build();
        orderDao.insertSeckillOrder(seckillOrder);

        return orderInfo;
    }
}
