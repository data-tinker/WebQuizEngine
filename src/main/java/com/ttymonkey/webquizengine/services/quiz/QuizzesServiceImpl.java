package com.ttymonkey.webquizengine.services.quiz;

import com.ttymonkey.webquizengine.mappers.QuizMapper;
import com.ttymonkey.webquizengine.models.dto.QuizCreationRequest;
import com.ttymonkey.webquizengine.models.dto.QuizResponse;
import com.ttymonkey.webquizengine.models.errors.QuizNotFound;
import com.ttymonkey.webquizengine.repositories.QuizzesRepository;
import com.ttymonkey.webquizengine.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class QuizzesServiceImpl implements QuizzesService {
    public static final int PAGE_SIZE = 10;
    private final QuizMapper mapper;
    private final QuizzesRepository quizzesRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public QuizzesServiceImpl(
        QuizMapper mapper,
        QuizzesRepository quizzesRepository,
        UsersRepository usersRepository
    ) {
        this.mapper = mapper;
        this.quizzesRepository = quizzesRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Page<QuizResponse> getAll(int page) {
        Pageable quizzesPageRequest = PageRequest.of(page, PAGE_SIZE);

        var quizzesPageResponse = quizzesRepository.findAll(quizzesPageRequest);

        var quizzes = quizzesPageResponse.hasContent() ?
                quizzesPageResponse.getContent().stream().map(mapper::mapQuizEntityToQuizResponse).collect(Collectors.toList()) :
                new ArrayList<QuizResponse>();

        return new PageImpl<>(
            quizzes,
            quizzesPageRequest,
            quizzesPageResponse.getTotalElements()
        );
    }

    @Override
    public QuizResponse getById(long id) {
        var entity = quizzesRepository.findQuizEntityById(id);

        if (entity == null) {
            throw new QuizNotFound();
        }

        return mapper.mapQuizEntityToQuizResponse(entity);
    }

    @Override
    public QuizResponse create(QuizCreationRequest newQuiz, String username) {
        var entity = mapper.mapQuizCreationRequestToQuizEntity(newQuiz);
        entity.setUser(usersRepository.findUserEntityByUsername(username));

        quizzesRepository.save(entity);

        return mapper.mapQuizEntityToQuizResponse(entity);
    }

    @Override
    public void deleteById(long id) {
        var entity = quizzesRepository.findQuizEntityById(id);

        if (entity == null) {
            throw new QuizNotFound();
        }

        quizzesRepository.deleteQuizEntityById(id);
    }

    @Override
    public boolean isAuthor(long id, String username) {
        var entity = quizzesRepository.findQuizEntityById(id);

        if (entity == null) {
            throw new QuizNotFound();
        }

        return entity.getUser().getUsername().equals(username);
    }
}
