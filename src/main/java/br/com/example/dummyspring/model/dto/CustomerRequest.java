package br.com.example.dummyspring.model.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CustomerRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    private AddressDto address;
}
