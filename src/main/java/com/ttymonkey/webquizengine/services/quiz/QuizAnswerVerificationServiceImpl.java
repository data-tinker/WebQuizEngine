package com.ttymonkey.webquizengine.services.quiz;

import com.ttymonkey.webquizengine.models.entities.AnswerEntity;
import com.ttymonkey.webquizengine.repositories.QuizzesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuizAnswerVerificationServiceImpl implements QuizAnswerVerificationService {
    private final QuizzesRepository quizzesRepository;

    @Autowired
    public QuizAnswerVerificationServiceImpl(
        QuizzesRepository quizzesRepository
    ) {
        this.quizzesRepository = quizzesRepository;
    }

    public boolean verifyAnswer(long quizId, Set<Integer> answer) {
        if (answer == null) {
            return false;
        }

        var record = quizzesRepository.findQuizEntityById(quizId);

        return answer.equals(record.getAnswers().stream().map(AnswerEntity::getValue).collect(Collectors.toSet()));
    }
}
