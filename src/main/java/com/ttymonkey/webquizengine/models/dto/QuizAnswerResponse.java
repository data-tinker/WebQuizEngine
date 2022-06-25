package com.ttymonkey.webquizengine.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuizAnswerResponse {
    private final boolean success;
    private final String feedback;
}
