package com.api.back.service.User;

import com.api.back.entities.User;

public interface IUserService {
    String createUser(CreateUserRequest request);

    FindUserResponse findUserByEmail(String email);

    User getUser(String email);

}

