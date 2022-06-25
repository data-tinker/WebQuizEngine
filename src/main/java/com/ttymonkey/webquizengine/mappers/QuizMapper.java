package com.ttymonkey.webquizengine.mappers;

import com.ttymonkey.webquizengine.models.dto.QuizCompleted;
import com.ttymonkey.webquizengine.models.dto.QuizCreationRequest;
import com.ttymonkey.webquizengine.models.dto.QuizResponse;
import com.ttymonkey.webquizengine.models.entities.QuizCompletedEntity;
import com.ttymonkey.webquizengine.models.entities.QuizEntity;

public interface QuizMapper {
    QuizResponse mapQuizEntityToQuizResponse(QuizEntity entity);
    QuizEntity mapQuizCreationRequestToQuizEntity(QuizCreationRequest creationRequest);

    QuizCompleted mapQuizCompletedEntityToQuizCompleted(QuizCompletedEntity entity);
}
