package ru.mobile.effective.socialmedia.controller.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mobile.effective.socialmedia.model.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private Role role;
}
