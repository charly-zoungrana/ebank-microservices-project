package net.zoungrana.ebankservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BankAccountDTO {
    private String type;
    private double balance;
    private Long customerId;
}
