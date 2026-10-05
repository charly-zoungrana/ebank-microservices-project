package net.zoungrana.customerservice.web;

import lombok.RequiredArgsConstructor;
import net.zoungrana.customerservice.entities.Customer;
import net.zoungrana.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable UUID id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public void saveCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
    }
}
