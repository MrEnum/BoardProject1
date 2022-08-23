package com.example.boardproject1.service;

import com.example.boardproject1.aop.exception.PostApiException;
import com.example.boardproject1.dto.PostRequestDto;
import com.example.boardproject1.dto.PostsResponseDto;
import com.example.boardproject1.model.Comments;
import com.example.boardproject1.model.Post;
import com.example.boardproject1.model.User;
import com.example.boardproject1.model.UserDetailsImpl;
import com.example.boardproject1.repository.CommentsRepository;
import com.example.boardproject1.repository.PostRepository;
import com.example.boardproject1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;

    @Transactional
    public void createPost(PostRequestDto postRequestDto, int userId, MultipartFile file) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new PostApiException("요청하신 작업이 실패했습니다.")
        );

        if (file.isEmpty()) {
            postRequestDto.setImageUrl(null);
        } else {
            postRequestDto.setImageUrlAndFileName(s3Service.upload(file));
        }
    }

    //게시판 전체 목록 조회(제목, 작성자 이름만)
    public List<PostsResponseDto> getAllPosts() {
        List<PostsResponseDto> postsResponseDtos = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            String title = post.getTitle();
            String nickname = post.getUser().getNickname();

            PostsResponseDto postsResponseDto = new PostsResponseDto(title, nickname);
            postsResponseDtos.add(postsResponseDto);
        }
        return postsResponseDtos;
    }


    public void deletePost(int userId, int postId) throws IllegalAccessException {
        Post post = postRepository.findByUserIdAndId(userId, postId);
        if (post ==null) {
            throw new IllegalAccessException("해당 글이 존재하지 않거나 권한이 없습니다.");
        }
        //이미지 url 삭제
        s3Service.deleteImageUrl(post.getImageUrl());

        //게시글 삭제 시 댓글 삭제
        List<Comments> comments = commentsRepository.findAllByPostId(postId);
        for(Comments comment : comments) {
            commentsRepository.delete(comment);
        }
        //게시글 삭제
        postRepository.deleteById(postId);
    }
}
