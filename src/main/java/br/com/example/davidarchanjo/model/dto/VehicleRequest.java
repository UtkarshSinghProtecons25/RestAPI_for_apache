package br.com.example.davidarchanjo.model.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class VehicleRequest {

    @NotBlank
    @Size(min = 17, max = 17)
    private String vin;

    @NotNull
    private Integer year;

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    private String trim;
    private String color;
    private String vehicleType;

    private Boolean totalLoss;

    private String titleStatus;
    private String registrationState;

    private String lenderId;
}