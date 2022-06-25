package com.ttymonkey.webquizengine.mappers;

import com.ttymonkey.webquizengine.models.dto.User;
import com.ttymonkey.webquizengine.models.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    private final PasswordEncoder encoder;

    @Autowired
    public UserMapperImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserEntity mapUserToUserEntity(User user) {
        return new UserEntity(
            user.getEmail(),
            encoder.encode(user.getPassword())
        );
    }
}
