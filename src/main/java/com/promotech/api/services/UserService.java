package com.promotech.api.services;

import com.promotech.api.domain.user.User;
import com.promotech.api.domain.user.dto.LoginRequestDTO;
import com.promotech.api.domain.user.dto.LoginResponseDTO;
import com.promotech.api.domain.user.dto.RegisterRequestDTO;
import com.promotech.api.infra.security.TokenService;
import com.promotech.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO authenticate(LoginRequestDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        // get logged-in user
        User user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return new LoginResponseDTO(token, user.getUsername(), user.getRole());
    }

    public void register(RegisterRequestDTO dto) {
        if (userRepository.findByUsername(dto.username()) != null) {
            throw new IllegalArgumentException("Username já em uso.");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.username(), encryptedPassword, dto.role());

        userRepository.save(newUser);
    }
}
