package com.ttymonkey.webquizengine.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class QuizResponse {
    private final long id;
    private final String title;
    private final String text;
    private final List<String> options;
}
