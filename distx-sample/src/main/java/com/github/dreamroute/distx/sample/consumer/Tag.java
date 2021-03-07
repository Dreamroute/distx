package com.github.dreamroute.distx.sample.consumer;

public class Tag {
    private Tag() {}

    // 创建订单
    public static final String ORDER_CREATE = "order-create";

    // 扣减库存
    public static final String WMS_DECREMENT = "wms-decrement";

    // Oneway
    public static final String ONE_WAY = "one-way";
}
