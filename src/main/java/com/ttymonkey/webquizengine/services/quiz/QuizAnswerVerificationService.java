package com.ttymonkey.webquizengine.services.quiz;

import java.util.Set;

public interface QuizAnswerVerificationService {
    boolean verifyAnswer(long quizId, Set<Integer> quizAnswer);
}
