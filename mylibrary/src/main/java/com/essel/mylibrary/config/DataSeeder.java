package com.essel.mylibrary.config;



import com.essel.mylibrary.user.Role;
import com.essel.mylibrary.user.User;
import com.essel.mylibrary.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${setup.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            var admin = User.builder()
                    .username("esseladmin")
                    .email("essel@example.com")
                    .password(passwordEncoder.encode(adminPassword))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println("admin created successfully.");
        } else {
            System.out.println("admin already exists, skipping creation.");
        }
    }
}

