package com.sparta.springlv1.controller;

import com.sparta.springlv1.dto.PostRequestDto;
import com.sparta.springlv1.dto.PostResponseDto;
import com.sparta.springlv1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    //게시글 전체조회
    @GetMapping("/posts")
    public List<PostResponseDto> getAllPosts(){
        return postService.getAllPosts();
    }

    //게시글 단건 조회

    @GetMapping("/posts/{postId}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    //게시글 작성
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);

    }
    //게시글 수정
    @PutMapping("/posts/{postId}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.updatePost(id,postRequestDto);

    }

    @DeleteMapping("posts/{postId}")
    public PostResponseDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        postService.deletePost(id,postRequestDto.getPassword());
        return new PostResponseDto(true);
    }



}
