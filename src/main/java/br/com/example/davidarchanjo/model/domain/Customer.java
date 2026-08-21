package br.com.example.davidarchanjo.model.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String customerId;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String status;
    private Instant createdAt;
}