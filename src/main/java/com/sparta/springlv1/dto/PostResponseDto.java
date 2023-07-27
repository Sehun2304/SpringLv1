package com.sparta.springlv1.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.springlv1.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)//널값이 아닌 경우에만 반환
public class PostResponseDto {

    private Long id;
    private String title;
    private String name;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean success;


    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.name = post.getName();
        this.contents = post.getContent();
        this.createdAt = post.getCreateAt();
        this.modifiedAt = post.getModifiedAt();

    }

    public PostResponseDto(Boolean success){
        this.success = success;
    }

}
