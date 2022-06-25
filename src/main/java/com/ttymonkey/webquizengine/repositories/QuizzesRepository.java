package com.ttymonkey.webquizengine.repositories;

import com.ttymonkey.webquizengine.models.entities.QuizEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface QuizzesRepository extends PagingAndSortingRepository<QuizEntity, Long> {
    QuizEntity findQuizEntityById(Long id);
    void deleteQuizEntityById(Long id);
}
