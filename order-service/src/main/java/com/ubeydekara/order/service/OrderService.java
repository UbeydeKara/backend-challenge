package com.ubeydekara.order.service;

import com.ubeydekara.order.model.Order;
import com.ubeydekara.payload.OrderPayload;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> getAll();

    Order create(Order order);

    Order update(Order order);

    Boolean deleteById(UUID id);

    List<Order> getOrdersAfterDate(LocalDateTime dateTime);
    List<OrderPayload> findAllByCustomerId(UUID customerId);
    List<UUID> getCustomerIds();
}
