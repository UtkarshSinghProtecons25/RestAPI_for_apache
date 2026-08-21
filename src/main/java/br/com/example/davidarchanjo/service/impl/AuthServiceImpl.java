package br.com.example.davidarchanjo.service.impl;

import br.com.example.davidarchanjo.security.JwtService;
import br.com.example.davidarchanjo.builder.UserBuilder;
import br.com.example.davidarchanjo.exception.*;
import br.com.example.davidarchanjo.model.domain.*;
import br.com.example.davidarchanjo.model.dto.*;
import br.com.example.davidarchanjo.repository.UserRepository;
import br.com.example.davidarchanjo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User u = repository.findByEmail(dto.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
        if (!passwordEncoder.matches(dto.getPassword(), u.getPassword()))
            throw new InvalidCredentialsException("Invalid email or password");
        return response(u);
    }

    private AuthResponseDTO response(User u) {
        return AuthResponseDTO.builder().token(jwt.generate(u)).tokenType("Bearer").expiresIn(3600).userId("USR-" + (10000 + u.getId())).role(u.getRole().name()).build();
    }
}
