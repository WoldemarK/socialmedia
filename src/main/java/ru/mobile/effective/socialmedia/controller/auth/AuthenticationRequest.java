package ru.mobile.effective.socialmedia.controller.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "DTO", description = "DTO для Authentication")
public class AuthenticationRequest {

    private String email;
    private String password;
}
