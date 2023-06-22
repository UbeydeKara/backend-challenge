package com.ubeydekara.order.mapper;

import com.ubeydekara.order.model.Order;
import com.ubeydekara.payload.OrderPayload;

public class OrderMapper {
    public static OrderPayload orderToPayload(Order order) {
        return OrderPayload.builder()
                .orderId(order.getId())
                .customerId(order.getCustomerId())
                .createDate(order.getCreateDate())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
