package com.api.back.service.Security;

import java.util.UUID;

public interface IJwtService {
    String generateToken(UUID userId);
    boolean isValidToken(String token, UUID userid);
}
