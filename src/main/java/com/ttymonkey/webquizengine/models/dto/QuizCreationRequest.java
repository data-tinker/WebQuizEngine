package com.ttymonkey.webquizengine.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public class QuizCreationRequest {
    @NotEmpty(message = "title should be passed")
    private final String title;

    @NotEmpty(message = "text should be passed")
    private final String text;

    @NotNull
    @Size(min = 2)
    private final List<String> options;

    private final Set<Integer> answer;
}
