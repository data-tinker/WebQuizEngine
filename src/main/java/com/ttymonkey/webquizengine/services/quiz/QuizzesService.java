package com.ttymonkey.webquizengine.services.quiz;

import com.ttymonkey.webquizengine.models.dto.QuizCreationRequest;
import com.ttymonkey.webquizengine.models.dto.QuizResponse;
import org.springframework.data.domain.Page;

public interface QuizzesService {
    Page<QuizResponse> getAll(int page);
    QuizResponse getById(long id);
    QuizResponse create(QuizCreationRequest newQuiz, String username);
    void deleteById(long id);
    boolean isAuthor(long id, String username);
}
