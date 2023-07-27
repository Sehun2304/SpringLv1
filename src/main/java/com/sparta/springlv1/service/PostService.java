package com.sparta.springlv1.service;

import com.sparta.springlv1.dto.PostRequestDto;
import com.sparta.springlv1.dto.PostResponseDto;
import com.sparta.springlv1.entity.Post;
import com.sparta.springlv1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public List<PostResponseDto> getAllPosts(){
        List<Post> postList = postRepository.findAllOrderByCreateAtDesc();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : postList){
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return postResponseDtoList;
    }

    public PostResponseDto getPost(Long id){
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    public PostResponseDto createPost(PostRequestDto postRequestDto){

        Post post = new Post(postRequestDto);

        Post savePost = postRepository.save(post);
        return  new PostResponseDto(savePost);
    }


    //데이터베이스를 다룰 때 트랜잭션을 적용하면 데이터
    // 추가, 갱신, 삭제 등으로 이루어진 작업을 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다. 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다.

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto){
        Post post = findPost(id);

        post.checkPassword(postRequestDto.getPassword());

        post.setTitle(postRequestDto.getTitle());
        post.setName(postRequestDto.getName());
        post.setContent(postRequestDto.getContent());

        return new PostResponseDto(post);}

    public void deletePost(Long id, String password){
        Post post = findPost(id);

        post.checkPassword(password);

        postRepository.delete(post);

    }

    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }
}
