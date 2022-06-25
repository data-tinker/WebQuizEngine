package com.ttymonkey.webquizengine.services.quiz;

import com.ttymonkey.webquizengine.models.dto.QuizCompleted;
import org.springframework.data.domain.Page;

public interface QuizzesCompletedService {
    void setQuizCompletedByUser(long quizId, String username);

    Page<QuizCompleted> findAllByUsername(String user, int page);

}
