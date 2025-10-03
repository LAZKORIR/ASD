package com.bank.cams.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable=false, unique=true) private String accountNumber;
    @Column(nullable=false) private String accountType;
    private LocalDate dateOpened;
    @Column(nullable=false) private Double balance;

    // Owning side, single FK lives here
    @OneToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId", unique = true)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private Customer customer;
}
