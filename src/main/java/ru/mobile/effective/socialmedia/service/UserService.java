package ru.mobile.effective.socialmedia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.mobile.effective.socialmedia.model.Post;
import ru.mobile.effective.socialmedia.model.User;
import ru.mobile.effective.socialmedia.repository.PostRepository;
import ru.mobile.effective.socialmedia.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public List<Post> viewPostsByOtherUsers(Long user_id) {
        return postRepository.getAllPostUserId(user_id);
    }

    public void createNewUser(@Validated User user) {
        Objects.requireNonNull(user, "Пришло то не знаю что");
        userRepository.save(user);
    }
}

