package com.imooc.miaosha.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SeckillGoods {
    private Long id;
    private Long goodsId;
    private Double seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
