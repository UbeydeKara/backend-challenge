package com.ubeydekara.customer.service;

import com.ubeydekara.clients.OrderClient;
import com.ubeydekara.customer.mapper.CustomerMapper;
import com.ubeydekara.customer.model.Customer;
import com.ubeydekara.customer.repository.CustomerRepository;
import com.ubeydekara.customer.response.CustomerResponse;
import com.ubeydekara.payload.CustomerPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final OrderClient orderClient;

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(customer ->
                customer.setOrders(orderClient.findAllByCustomerId(customer.getId())));
        return customers;
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer update(Customer customer) {
        customer.setOrders(orderClient.findAllByCustomerId(customer.getId()));
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Boolean deleteById(UUID id) {
        Boolean exists = customerRepository.existsById(id);
        customerRepository.deleteById(id);
        return exists;
    }

    @Override
    public List<Customer> getCustomersWithoutOrder() {
        List<UUID> customerIds = orderClient.getCustomerIds();
        return customerRepository.findAllByIdNotIn(customerIds);
    }

    @Override
    public List<CustomerResponse> findByNameContaining(String name) {
        return customerRepository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    @Override
    public CustomerPayload getById(UUID customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::toPayload)
                .orElse(null);
    }
}
