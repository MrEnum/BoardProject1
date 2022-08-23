package com.example.boardproject1.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String contents;

    private String imageUrl;
    private String fileName;

    public  void setImageUrlAndFileName(FileRequestDto fileRequestDto){
        this.imageUrl = fileRequestDto.getImageUrl();
    }
}
