package net.zoungrana.ebankservice.model;

import lombok.*;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
    private Long id;
    private String name;
    private String email;
}
