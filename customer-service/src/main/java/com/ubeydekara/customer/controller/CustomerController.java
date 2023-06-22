package com.ubeydekara.customer.controller;

import com.ubeydekara.customer.model.Customer;
import com.ubeydekara.customer.response.CustomerResponse;
import com.ubeydekara.customer.service.CustomerService;
import com.ubeydekara.payload.CustomerPayload;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody @Valid Customer customer) {
        return customerService.create(customer);
    }

    @PutMapping
    public Customer update(@RequestBody @Valid Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        if (customerService.deleteById(id))
            return ResponseEntity.ok("Customer deleted successfuly with id: " + id);
        log.warn("Customer not found with id: " + id);
        return ResponseEntity.badRequest().body("Customer not found with id: " + id);
    }

    @GetMapping("/without-order")
    public List<Customer> getCustomersWithoutOrder() {
        return customerService.getCustomersWithoutOrder();
    }

    @GetMapping("/find/{name}")
    public List<CustomerResponse> findByNameContaining(@PathVariable String name) {
        return customerService.findByNameContaining(name);
    }

    @GetMapping("/get/{customerId}")
    CustomerPayload getById(@PathVariable("customerId") UUID customerId) {
        return customerService.getById(customerId);
    }
}
