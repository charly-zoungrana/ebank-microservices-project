package net.zoungrana.customerservice;

import net.zoungrana.customerservice.entities.Customer;
import net.zoungrana.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerService customerService){

        return args -> {

            List<String> names=List.of("John","Jane","Rachid");
            names.forEach((name)->{
                Customer customer=Customer.builder()
                        .name(name)
                        .email(name+"@gmail.com")
                        .build();
                customerService.saveCustomer(customer);
            });
        };

    }

}
