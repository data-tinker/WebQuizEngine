package com.ttymonkey.webquizengine.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class QuizAnswerRequest {
    private Set<Integer> answer;
}
