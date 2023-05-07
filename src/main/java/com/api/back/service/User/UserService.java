package com.api.back.service.User;

import com.api.back.data.UserRepository;
import com.api.back.entities.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private EventService _eventService;

    public UserService(UserRepository userRepository) {
        _userRepository = userRepository;
    }




    @Async
    public String createUser(@NotNull CreateUserRequest request) {
        var user = new User (request.name, request.email, request.password);


         if  (!_userRepository.findUserByEmail(request.email).isEmpty()){
              return null;
         }

           _userRepository.save(user);

           _eventService.send( "", user.getId().toString());

                return user.getId().toString();

    }

    public FindUserResponse findUserByEmail(String email) {

       var user = _userRepository.findUserByEmail(email).get();

       var response = new FindUserResponse (user.getId(), user.getName(), user.getEmail());

       return response;
    }

    public User getUser(String email) {
        return _userRepository.findUserByEmail(email).get();
    }
}
