package net.zoungrana.customerservice.service;

import lombok.RequiredArgsConstructor;
import net.zoungrana.customerservice.dto.CustomerDTO;
import net.zoungrana.customerservice.entities.Customer;
import net.zoungrana.customerservice.repository.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @McpTool(description = "Get all customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @McpTool(description="Find Customer By id")
    public Customer getCustomerById(@McpToolParam(description = "The id of the customer") Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found"));
    }


    public void saveCustomer( Customer customer){
        customerRepository.save(customer);
    }

    @McpTool(description="Save Customer")
    public void saveCustomerByAIAgent(@McpToolParam(description = "Customer to save (name,email)") CustomerDTO customer){
        Customer entity=Customer.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
        customerRepository.save(entity);
    }
}
