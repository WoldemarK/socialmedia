package ru.mobile.effective.socialmedia.controller.auth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mobile.effective.socialmedia.security.service.AuthenticationService;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@Tag(name = "Контроллер регистрации и аутентификации",
        description = "Метод register предназначен для регистрации нового пользователя, " +
                "всем новым пользователям по умолчанию присваивается роль User." +
                "Метод authenticate, предназначен для аутентификации зарегистрированных пользователей в системе ")
public class AuthenticationController {

    private final AuthenticationService service;

    @Operation
            (
                    summary = "Регистрация нового пользователя",
                    description = "Регистрация нового пользователя"
            )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody
                                                               @Parameter(description = "RegisterRequest, DTO на выбранные поля User")
                                                               RegisterRequest request) {
        Objects.requireNonNull(request, "Пользователь не найден");
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody
                                                                   @Parameter(description = "RegisterRequest, DTO на выбранные поля User")
                                                                   AuthenticationRequest request) {
        Objects.requireNonNull(request, "Пользователь не найден");
        return ResponseEntity.ok(service.authenticate(request));
    }

}
