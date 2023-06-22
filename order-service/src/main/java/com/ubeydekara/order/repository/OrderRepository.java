package com.ubeydekara.order.repository;

import com.ubeydekara.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByCreateDateAfter(LocalDateTime dateTime);
    List<Order> findAllByCustomerId(UUID customerId);
}
