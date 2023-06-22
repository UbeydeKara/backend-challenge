package com.ubeydekara.clients;

import com.ubeydekara.payload.OrderPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping(path = "/api/v1/order/customer/{customerId}")
    List<OrderPayload> findAllByCustomerId(@PathVariable("customerId") UUID customerId);

    @GetMapping(path = "/api/v1/order/customers")
    List<UUID> getCustomerIds();
}
