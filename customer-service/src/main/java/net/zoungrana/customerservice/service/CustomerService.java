package net.zoungrana.customerservice.service;

import lombok.RequiredArgsConstructor;
import net.zoungrana.customerservice.entities.Customer;
import net.zoungrana.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found"));
    }

    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }
}
