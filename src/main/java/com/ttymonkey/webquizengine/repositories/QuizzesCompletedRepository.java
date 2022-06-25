package com.ttymonkey.webquizengine.repositories;

import com.ttymonkey.webquizengine.models.entities.QuizCompletedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzesCompletedRepository extends CrudRepository<QuizCompletedEntity, Long> {
    @Query(value = "SELECT c FROM QuizCompletedEntity c WHERE c.user.username = :username")
    Page<QuizCompletedEntity> findAllByUsername(@Param("username") String username, Pageable pageable);
}
