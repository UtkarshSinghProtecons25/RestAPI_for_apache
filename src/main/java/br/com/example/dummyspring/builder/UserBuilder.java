package br.com.example.dummyspring.builder;

import br.com.example.dummyspring.model.domain.User;
import br.com.example.dummyspring.model.dto.AuthResponseDTO;
import br.com.example.dummyspring.model.dto.SignupRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

    public User build(SignupRequestDTO dto, String encodedPassword) {
        return User.builder()
            .email(dto.getEmail())
            .password(encodedPassword)
            .fullName(dto.getFullName())
            .build();
    }

    public AuthResponseDTO buildAuthResponse(User user) {
        return AuthResponseDTO.builder()
                .userId(user.getId().toString())
                .role(user.getRole().name())
                .build();
    }
}
