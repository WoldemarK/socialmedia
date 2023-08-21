package ru.mobile.effective.socialmedia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mobile.effective.socialmedia.model.Post;
import ru.mobile.effective.socialmedia.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api/post")
@Tag(name = "Post Controller", description = "Реализация Controller ")
public class PostController {

    private final PostService postService;

    @Operation
            (
                    summary = "Создание нового post",
                    description = "Создание нового post, для конкретного User, по ID"
            )
    @PostMapping("/new/post/{user_id}")
    public ResponseEntity<Post> addingNewPost(@PathVariable(name = "user_id") Long user_id,
                                              @RequestBody Post post
    ) {
        return new ResponseEntity<>(postService.addingNewPost(user_id, post), HttpStatus.CREATED);

    }

    @Operation
            (
                    summary = "Отображение данных",
                    description = "Отображение данный  Post через пагинацию."
            )
    @GetMapping("/page/{id}")
    public ResponseEntity<List<Post>> getAll(@PathVariable(name = "id") Long id,
                                             @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                             @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        return new ResponseEntity<>(postService.findByUserIdAndPost(id, offset, limit),
                HttpStatus.OK);
    }

    @Operation
            (
                    summary = "Отображение данных",
                    description = "Отображение данный  List."
            )
    @GetMapping("/{id}")
    public ResponseEntity<List<Post>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.ACCEPTED);
    }
    @Operation
            (
                    summary = "Удаление данных по ID",
                    description = "Удаление данных"
            )
    @DeleteMapping("/{id}")
    public boolean deletePostById(Long id) {
        if (id == null) {
            throw new RuntimeException("ID " + id + " не найден");
        }
        postService.deletePostById(id);
        return true;
    }
    @Operation
            (
                    summary = "Обновление данных по ID",
                    description = "Обновление данных"
            )
    @PutMapping("/{id}")
    public boolean updatePost(@PathVariable(name = "id") Long id,
                              @RequestParam(name = "message", required = false) String message,
                              @RequestParam(name = "hider", required = false) String hider) {
        if (id == null) {
            throw new RuntimeException("ID: " + id + " не найден");
        }
        return postService.updatePost(id, message, hider);
    }
}
