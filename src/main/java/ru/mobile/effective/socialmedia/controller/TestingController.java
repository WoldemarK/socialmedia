package ru.mobile.effective.socialmedia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import ru.mobile.effective.socialmedia.model.Post;
import ru.mobile.effective.socialmedia.model.User;
import ru.mobile.effective.socialmedia.repository.PostRepository;
import ru.mobile.effective.socialmedia.repository.UserRepository;
import ru.mobile.effective.socialmedia.service.PostService;
import ru.mobile.effective.socialmedia.service.UserService;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Tag(name = "Test Controller", description = "Тестовый контроллер для тестирования нового функционала")
public class TestingController {

    private final UserService userService;
    public final PostService postService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Operation
            (
                    summary = "Создание нового пользователя",
                    description = "Тестовый controller, по созданию одного пользователя"
            )
    @PostMapping("/adding/new/user")
    public void createNewUser(@RequestBody
                              @Parameter(name = "User",
                                      description = "User поступает в виде JSON") User user) {
        userService.createNewUser(user);
    }

    @Operation
            (
                    summary = "Создание нового поста",
                    description = "Тестовый controller, по созданию одного Post"
            )
    @PostMapping("/adding/new/post")
    public void createNewPost(@RequestBody
                              @Parameter(name = "User",
                                      description = "Post поступает в виде JSON") Post post) {
        postService.createNewPost(post);
    }
}
