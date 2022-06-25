package com.ttymonkey.webquizengine.services.quiz;

import com.ttymonkey.webquizengine.mappers.QuizMapper;
import com.ttymonkey.webquizengine.models.dto.QuizCompleted;
import com.ttymonkey.webquizengine.models.entities.QuizCompletedEntity;
import com.ttymonkey.webquizengine.repositories.QuizzesCompletedRepository;
import com.ttymonkey.webquizengine.repositories.QuizzesRepository;
import com.ttymonkey.webquizengine.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class QuizzesCompletedServiceImpl implements QuizzesCompletedService {
    public static final int PAGE_SIZE = 10;

    public static final String SORT = "completedAt";
    private final QuizMapper mapper;

    private final QuizzesRepository quizzesRepository;

    private final UsersRepository usersRepository;

    private final QuizzesCompletedRepository quizzesCompletedRepository;

    @Autowired
    public QuizzesCompletedServiceImpl(
        QuizMapper mapper,
        QuizzesRepository quizzesRepository,
        UsersRepository usersRepository,
        QuizzesCompletedRepository quizzesCompletedRepository
    ) {
        this.mapper = mapper;
        this.quizzesRepository = quizzesRepository;
        this.usersRepository = usersRepository;
        this.quizzesCompletedRepository = quizzesCompletedRepository;
    }

    @Override
    public void setQuizCompletedByUser(long quizId, String username) {
        var quiz = quizzesRepository.findQuizEntityById(quizId);
        var user = usersRepository.findUserEntityByUsername(username);

        var quizCompleted = new QuizCompletedEntity(quiz, user);

        quizzesCompletedRepository.save(quizCompleted);
    }

    @Override
    public Page<QuizCompleted> findAllByUsername(String username, int page) {
        Pageable quizzesCompletedPageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by(SORT).descending());

        var quizzesCompletedPageResponse = quizzesCompletedRepository.findAllByUsername(username, quizzesCompletedPageRequest);

        var quizzesCompleted = quizzesCompletedPageResponse.hasContent() ?
                quizzesCompletedPageResponse.getContent().stream().map(mapper::mapQuizCompletedEntityToQuizCompleted).collect(Collectors.toList()) :
                new ArrayList<QuizCompleted>();

        return new PageImpl<>(
                quizzesCompleted,
                quizzesCompletedPageRequest,
                quizzesCompletedPageResponse.getTotalElements()
        );
    }
}
