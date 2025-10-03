package com.bank.cams.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable=false) private String firstName;
    @Column(nullable=false) private String lastName;

    @OneToOne(mappedBy = "customer")
    @com.fasterxml.jackson.annotation.JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Account account;
}
