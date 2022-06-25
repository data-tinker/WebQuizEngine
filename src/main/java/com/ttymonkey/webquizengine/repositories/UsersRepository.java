package com.ttymonkey.webquizengine.repositories;

import com.ttymonkey.webquizengine.models.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findUserEntityByUsername(String username);
}
