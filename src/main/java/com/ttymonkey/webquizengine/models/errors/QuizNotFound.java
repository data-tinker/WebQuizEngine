package com.ttymonkey.webquizengine.models.errors;

public class QuizNotFound extends RuntimeException {
    public QuizNotFound() {
        super("quiz not found");
    }
}