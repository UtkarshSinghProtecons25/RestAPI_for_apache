package br.com.example.dummyspring.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String tokenType;
    private long expiresIn;
    private String userId;
    private String role;
}
