package com.sparta.springlv1.repository;

import com.sparta.springlv1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllOrderByCreateAtDesc();
}
