package com.ubeydekara.clients;

import com.ubeydekara.payload.CustomerPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "customer-service")
public interface CustomerClient {
    @GetMapping(path = "/api/v1/customer/get/{customerId}")
    CustomerPayload getById(@PathVariable("customerId") UUID customerId);
}
