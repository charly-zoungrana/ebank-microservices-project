package net.zoungrana.ebankservice.entities;


import jakarta.persistence.*;
import lombok.*;
import net.zoungrana.ebankservice.model.Customer;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String type;
    private double balance;
    private LocalDate createdAt;
    private Long customerId;
    @Transient
    private Customer customer;
}
