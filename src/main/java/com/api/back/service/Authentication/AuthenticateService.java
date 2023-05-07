package com.api.back.service.Authentication;


import com.api.back.service.Security.IJwtService;
import com.api.back.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticateService implements IAuthenticateService {
    @Autowired
    private IUserService _userService;
    @Autowired
    private IJwtService _jwtService;



    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        var user  = _userService.getUser(request.email);


        if(user == null) {
            return null;
        }

        if (user.getPassword().equals(request.password)){
            System.out.println("Senha invalida!");
        }


        var Token = _jwtService.generateToken(user.getId());

        var response = new AuthenticateResponse();

        response.setUserId(user.getId());
        response.setToken(Token);

        return response;
    }
}
