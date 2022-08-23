package com.example.boardproject1.controller;


import com.example.boardproject1.dto.PostRequestDto;
import com.example.boardproject1.dto.PostsResponseDto;
import com.example.boardproject1.model.Post;
import com.example.boardproject1.model.UserDetailsImpl;
import com.example.boardproject1.repository.PostRepository;
import com.example.boardproject1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    // 게시글 업로드 /완성
    @PostMapping("/Posting")
    public String uploadPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                             @RequestPart(value = "postDto") PostRequestDto postRequestDto,
                             @RequestPart(value = "file")MultipartFile file){
        int user = userDetails.getUser().getId();
        postService.createPost(postRequestDto, user, file);
        return "업로드가 완료되었습니다.";
    }

    //게시글 전체 조회 / 완성
    @GetMapping("/posts")
    public List<PostsResponseDto> getPosts(){
       return postService.getAllPosts();
    }

    //게시글 조회 / 완성
    @GetMapping("/post/{postId}")
    public Optional<Post> getPost(@PathVariable int postId){
        return postRepository.findById(postId);
    }

    //게시글 수정

    //게시글 삭제
    @DeleteMapping("/post/{postId}/delete")
    public String deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                             @PathVariable int postId) throws IllegalAccessException {
        postService.deletePost(userDetails.getUser().getId(), postId);
        return "삭제가 완료되었습니다.";
    }
}
