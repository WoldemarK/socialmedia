package ru.mobile.effective.socialmedia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.mobile.effective.socialmedia.controller.auth.RegisterRequest;
import ru.mobile.effective.socialmedia.model.Role;
import ru.mobile.effective.socialmedia.security.service.AuthenticationService;

import static ru.mobile.effective.socialmedia.model.Role.*;

@SpringBootApplication
public class SocialmediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialmediaApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service){
        return args -> {
            var admin = RegisterRequest.builder()
                    .username("Admin")
                    .role(ADMIN)
                    .password("password")
                    .email("ad@ad.ad")
                    .build();

            System.out.println("Admin token: " + service.register(admin).getAccessToken());
        };
    }
}
