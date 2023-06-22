package com.ubeydekara.order.controller;

import com.ubeydekara.order.model.Order;
import com.ubeydekara.order.service.OrderService;
import com.ubeydekara.payload.OrderPayload;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Order order) {
        Order newOrder = orderService.create(order);
        if (newOrder == null) {
            log.warn("Customer must be present. No customer found with id: " + order.getCustomerId());
            return ResponseEntity.badRequest()
                    .body("Customer must be present. No customer found with id: " + order.getCustomerId());
        }
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping
    public Order update(@RequestBody @Valid Order order) {
        return orderService.update(order);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        if (orderService.deleteById(id))
            return ResponseEntity.ok("Order deleted successfuly with id: " + id);
        log.warn("Order not found with id: " + id);
        return ResponseEntity.badRequest().body("Order not found with id: " + id);
    }

    @GetMapping("/customer/{customerId}")
    List<OrderPayload> findAllByCustomerId(@PathVariable UUID customerId) {
        return orderService.findAllByCustomerId(customerId);
    }

    @GetMapping("/customers")
    public List<UUID> getCustomerIds() {
        return orderService.getCustomerIds();
    }

    @GetMapping("/date")
    public List<Order> getOrdersAfterDate(@RequestParam String after) {
        return orderService.getOrdersAfterDate(LocalDateTime.of(LocalDate.parse(after), LocalTime.now()));
    }
}
