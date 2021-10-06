package com.imooc.miaosha.vo;

import com.imooc.miaosha.domain.SeckillUser;
import lombok.Data;

@Data
public class GoodsDetailVo {
    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods;
    private SeckillUser user;
}
