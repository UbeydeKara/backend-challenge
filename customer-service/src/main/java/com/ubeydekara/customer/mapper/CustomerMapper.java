package com.ubeydekara.customer.mapper;

import com.ubeydekara.clients.OrderClient;
import com.ubeydekara.customer.model.Customer;
import com.ubeydekara.customer.response.CustomerResponse;
import com.ubeydekara.payload.CustomerPayload;
import com.ubeydekara.payload.OrderPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomerMapper {

    private final OrderClient orderClient;

    public CustomerResponse toResponse(Customer customer) {
        List<OrderPayload> orders = orderClient.findAllByCustomerId(customer.getId());
        return CustomerResponse.builder()
                .orderIds(orders.stream().map(OrderPayload::getOrderId).toList()) // convert class to id list
                .age(customer.getAge())
                .name(customer.getName())
                .Id(customer.getId())
                .build();
    }

    public CustomerPayload toPayload(Customer customer) {
        List<OrderPayload> orders = orderClient.findAllByCustomerId(customer.getId());
        return CustomerPayload.builder()
                .customerId(customer.getId())
                .name(customer.getName())
                .age(customer.getAge())
                .orders(orders)
                .build();
    }
}
