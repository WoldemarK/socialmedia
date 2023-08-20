package ru.mobile.effective.socialmedia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.mobile.effective.socialmedia.model.Post;
import ru.mobile.effective.socialmedia.model.User;
import ru.mobile.effective.socialmedia.repository.PostRepository;
import ru.mobile.effective.socialmedia.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /**
     * ПОСЛЕ ТЕСТОВ УДАЛИТЬ
     *
     * @param post
     */
    @Transactional
    public void createNewPost(@Validated Post post) {
        log.info("{createNewPost} " + post);
        Objects.requireNonNull(post, "Пришло то не знаю что");
        postRepository.save(post);

    }

    /**
     * Метод создает новый post для конкретного пользователя по его ID
     *
     * @param id
     * @param post
     * @return
     */
    @Transactional
    public Post addingNewPost(Long id, Post post) {
        User user = userRepository.findById(id).get();
        log.info("Method {addingNewPost}" + id + " " + post);
        Objects.requireNonNull(user.getId(), "Пользователь с ID: " + user.getId() + " не найден");
        user.addingPost(post);
        post.setUser(user);
        return postRepository.save(post);
    }

    /**
     * Обновление post-a по ID
     *
     * @param id
     * @param message
     * @param hider
     * @return
     */
    @Transactional
    public boolean updatePost(Long id, String message, String hider) {
        log.info("Method {updatePost}" + id + " " + message + " " + hider);
        Optional<Post> post = postRepository.findById(id);
        Objects.requireNonNull(id, "Пост с ID: " + " не найде");
        Post result = post.get();
        result.setMessage(message);
        result.setHider(hider);
        return true;
    }

    /**
     * Удаление post-a по ID
     *
     * @param id
     * @return
     */
    @Transactional
    public boolean deletePostById(Long id) {
        log.info("Method {deletePostById}" + id);
        Optional<Post> delete = postRepository.findById(id);
        Objects.requireNonNull(id, "Пост с ID: " + " не найде");
        postRepository.deleteById(id);
        return true;
    }

    /**
     * Показ всех Posto-в
     *
     * @return
     */
    public List<Post> getAllPosts() {
        log.info("Method {getAllPosts}");
        return postRepository.findAll();
    }

    /**
     * По ID пользователя найти все посты, и предоставить отображение страниц в Pagination
     * @param id
     * @param page
     * @param pageSize
     * @return
     */
    public List<Post> findByUserIdAndPost(Long id, int page, int pageSize) {
        log.info("Method {findByUserIdAndPost}");
        Objects.requireNonNull(id, "Method {findByUserIdAndPost} ID: " + id + " не существует");
        Sort sort = Sort.by(Sort.Direction.DESC);
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return postRepository.findByPostByUserId(id,pageable);
    }
}