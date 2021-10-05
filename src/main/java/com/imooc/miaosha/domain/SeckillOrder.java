package com.imooc.miaosha.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;
}
