package com.example.boardproject1.aop.exception;

public class PostApiException extends RuntimeException {
    private static final String msg = "입력 정보를 다시 확인해 주세요.";

    public PostApiException(String msg) {
        super(msg);
    }
}
