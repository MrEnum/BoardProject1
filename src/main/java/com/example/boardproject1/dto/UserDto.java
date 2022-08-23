package com.example.boardproject1.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String nickname;
    private String name;

    @Builder
    public UserDto(String username, String password, String nickname, String name){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
    }
}
