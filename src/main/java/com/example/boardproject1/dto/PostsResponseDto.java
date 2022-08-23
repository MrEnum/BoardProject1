package com.example.boardproject1.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PostsResponseDto {
    private String title;
    private String nickname;

    public PostsResponseDto(String title, String nickname){
        this.title = title;
        this.nickname = nickname;
    }
}
