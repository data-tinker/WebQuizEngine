package com.ttymonkey.webquizengine.mappers;

import com.ttymonkey.webquizengine.models.dto.User;
import com.ttymonkey.webquizengine.models.entities.UserEntity;

public interface UserMapper {
    UserEntity mapUserToUserEntity(User user);
}
