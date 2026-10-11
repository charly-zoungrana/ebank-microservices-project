package net.zoungrana.customerservice.repository;

import net.zoungrana.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
