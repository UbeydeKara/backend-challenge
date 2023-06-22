package com.ubeydekara.customer.repository;

import com.ubeydekara.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAllByNameContainingIgnoreCase(String name);
    List<Customer> findAllByIdNotIn(List<UUID> idList);
}
