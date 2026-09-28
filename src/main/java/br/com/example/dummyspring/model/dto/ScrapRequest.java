package br.com.example.dummyspring.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ScrapRequest {

    @NotBlank
    private String actionOnVehicle;
}