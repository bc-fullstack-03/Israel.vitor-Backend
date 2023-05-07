package com.api.back.service.Authentication;

public interface IAuthenticateService {
    AuthenticateResponse authenticate(AuthenticateRequest request);
}
