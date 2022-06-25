package com.ttymonkey.webquizengine.mappers;

import com.ttymonkey.webquizengine.models.dto.QuizCompleted;
import com.ttymonkey.webquizengine.models.dto.QuizCreationRequest;
import com.ttymonkey.webquizengine.models.dto.QuizResponse;
import com.ttymonkey.webquizengine.models.entities.AnswerEntity;
import com.ttymonkey.webquizengine.models.entities.OptionEntity;
import com.ttymonkey.webquizengine.models.entities.QuizCompletedEntity;
import com.ttymonkey.webquizengine.models.entities.QuizEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuizMapperImpl implements QuizMapper {
    @Override
    public QuizResponse mapQuizEntityToQuizResponse(QuizEntity entity) {
        return new QuizResponse(
            entity.getId(),
            entity.getTitle(),
            entity.getText(),
            entity.getOptions().stream().map(OptionEntity::getValue).collect(Collectors.toList())
        );
    }

    @Override
    public QuizEntity mapQuizCreationRequestToQuizEntity(QuizCreationRequest creationRequest) {
        return new QuizEntity(
            creationRequest.getTitle(),
            creationRequest.getText(),
            creationRequest.getOptions().stream().map(OptionEntity::new).collect(Collectors.toList()),
            creationRequest.getAnswer() == null ? Set.of() : creationRequest.getAnswer().stream().map(AnswerEntity::new).collect(Collectors.toSet())
        );
    }

    @Override
    public QuizCompleted mapQuizCompletedEntityToQuizCompleted(QuizCompletedEntity entity) {
        return new QuizCompleted(
            entity.getQuiz().getId(),
            entity.getCompletedAt()
        );
    }
}
