package com.ubeydekara.order.service;

import com.ubeydekara.clients.CustomerClient;
import com.ubeydekara.order.mapper.OrderMapper;
import com.ubeydekara.order.model.Order;
import com.ubeydekara.order.repository.OrderRepository;
import com.ubeydekara.payload.OrderPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;

    @Override
    public List<Order> getAll() {
        List<Order> orderList = orderRepository.findAll();
        orderList.forEach(order -> order.setCustomer(
                customerClient.getById(order.getCustomerId())
        ));
        return orderList;
    }

    @Override
    public Order create(Order order) {
        order.setCreateDate(LocalDateTime.now());
        order.setCustomer(customerClient.getById(order.getCustomerId()));
        if (order.getCustomer() == null)
            return null;
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order update(Order order) {
        Order newOrder = orderRepository.saveAndFlush(order);
        order.setCustomer(customerClient.getById(order.getCustomerId()));
        order.setCreateDate(LocalDateTime.now());
        return newOrder;
    }

    @Override
    public Boolean deleteById(UUID id) {
        Boolean exists = orderRepository.existsById(id);
        orderRepository.deleteById(id);
        return exists;
    }

    @Override
    public List<Order> getOrdersAfterDate(LocalDateTime dateTime) {
        List<Order> orderList = orderRepository.findAllByCreateDateAfter(dateTime);
        orderList.forEach(order -> order.setCustomer(
                customerClient.getById(order.getCustomerId())
        ));
        return orderList;
    }


    @Override
    public List<OrderPayload> findAllByCustomerId(UUID customerId) {
        return orderRepository.findAllByCustomerId(customerId)
                .stream()
                .map(OrderMapper::orderToPayload).toList();
    }

    @Override
    public List<UUID> getCustomerIds() {
        return orderRepository.findAll().stream().map(Order::getCustomerId).toList();
    }

}
