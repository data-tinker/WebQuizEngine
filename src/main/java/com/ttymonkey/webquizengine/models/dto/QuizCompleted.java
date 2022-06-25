package com.ttymonkey.webquizengine.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class QuizCompleted {
    private final long id;
    private final Date completedAt;
}
