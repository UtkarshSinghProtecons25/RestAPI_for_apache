package br.com.example.dummyspring.service;

import br.com.example.dummyspring.model.dto.AuthResponseDTO;
import br.com.example.dummyspring.model.dto.LoginRequestDTO;
import br.com.example.dummyspring.model.dto.SignupRequestDTO;

public interface AuthService {

    AuthResponseDTO signup(SignupRequestDTO dto);

    AuthResponseDTO login(LoginRequestDTO dto);
}
