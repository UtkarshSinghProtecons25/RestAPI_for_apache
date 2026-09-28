package br.com.example.dummyspring.service.impl;

import br.com.example.dummyspring.security.JwtService;
import br.com.example.dummyspring.builder.UserBuilder;
import br.com.example.dummyspring.exception.*;
import br.com.example.dummyspring.model.domain.*;
import br.com.example.dummyspring.model.dto.*;
import br.com.example.dummyspring.repository.UserRepository;
import br.com.example.dummyspring.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final UserBuilder builder;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwt;

    @Transactional
    public AuthResponseDTO signup(SignupRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) throw new UserAlreadyExistsException("User already exists");
        User u = builder.build(dto, passwordEncoder.encode(dto.getPassword()));
        u.setRole(Role.INSURANCE_AGENT);
        return response(repository.save(u));
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {

        Optional<User> userOptional = repository.findByEmail(dto.getEmail());

        /* Email does not exist */
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException(
                    "Invalid credentials. Please check your email or sign up."
            );
        }

        User u = userOptional.get();

        // Email exists, but password is incorrect
        if (!passwordEncoder.matches(dto.getPassword(), u.getPassword())) {
            throw new InvalidCredentialsException(
                    "Invalid credentials. Password is incorrect."
            );
        }

        return response(u);
    }

    private AuthResponseDTO response(User u) {
        return AuthResponseDTO.builder().token(jwt.generate(u)).tokenType("Bearer").expiresIn(3600).userId("USR-" + (10000 + u.getId())).role(u.getRole().name()).build();
    }
}
