package com.essel.mylibrary.auth;

import com.essel.mylibrary.user.Role;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequest {

    private String username;

    private String email;

    private String password;

    private Role role;



}
