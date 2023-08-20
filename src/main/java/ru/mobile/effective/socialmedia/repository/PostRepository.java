package ru.mobile.effective.socialmedia.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mobile.effective.socialmedia.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select * from post where user_id = ?", nativeQuery = true)
    List<Post> getAllPostUserId(Long id);

    @Query(value = "select p.message,p.hider from post p where user_id = ?", nativeQuery = true)
    List<Post> findByPostByUserId(Long id, Pageable pageable);

}
