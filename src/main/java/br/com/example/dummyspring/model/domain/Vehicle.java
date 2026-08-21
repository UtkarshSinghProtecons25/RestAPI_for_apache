package br.com.example.dummyspring.model.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String vehicleId;

    @Column(unique = true, nullable = false, length = 17)
    private String vin;

    private Integer year;
    private String make;
    private String model;
    private String trim;
    private String color;
    private String vehicleType;

    private Boolean totalLoss;

    @Column(nullable = false)
    private String lenderId;

    private String titleStatus;
    private String registrationState;

    @Column(nullable = false)
    private Long userId;

    private Instant createdAt;
}