package com.ttymonkey.webquizengine.services.user;

import com.ttymonkey.webquizengine.mappers.UserMapper;
import com.ttymonkey.webquizengine.models.dto.User;
import com.ttymonkey.webquizengine.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final UsersRepository repository;

    @Autowired
    public UserServiceImpl(
        UserMapper mapper,
        UsersRepository repository
    ) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void create(User user) {
        repository.save(mapper.mapUserToUserEntity(user));
    }
}
