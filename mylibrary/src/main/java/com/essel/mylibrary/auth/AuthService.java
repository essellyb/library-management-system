package com.essel.mylibrary.auth;

import com.essel.mylibrary.config.JwtService;
import com.essel.mylibrary.user.Role;
import com.essel.mylibrary.user.User;
import com.essel.mylibrary.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse registerAdmin(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

            var user = userRepository.findByEmail(request.getEmail()).
                    orElseThrow(() -> new RuntimeException("User not found"));

            var jwtToken = jwtService.generateToken(user);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole().equals(Role.USER)){
            userRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Not allowed.");
        }
    }

    public void deleteAdminById(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Admin not found"));

        if (user.getRole().equals(Role.ADMIN)){
            userRepository.deleteById(id);
        }

        else {
            throw new RuntimeException("Not allowed");
        }
    }
}
