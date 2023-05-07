package com.api.back.service.User;

import java.util.UUID;

import lombok.Data;
@Data
public class FindUserResponse {
    public UUID id;
    public String name;
    public String email;

    public FindUserResponse(UUID id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;

    }
}
