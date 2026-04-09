package com.essel.mylibrary.auth;

import com.essel.mylibrary.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequest {

    @NotBlank(message = "Username field must not be empty")
    @Size(min = 5, max = 20, message = "Username should be between 5 and 20 characters")
    private String username;

    @NotBlank(message = "Email field must not be empty")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Password field must not be empty")
    @Size(message = "Password must be more than 5 characters", min = 5 , max = 50)
    private String password;

    private Role role;



}
