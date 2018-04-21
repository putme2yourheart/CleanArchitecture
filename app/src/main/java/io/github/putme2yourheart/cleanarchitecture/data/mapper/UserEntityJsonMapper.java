package io.github.putme2yourheart.cleanarchitecture.data.mapper;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;

public class UserEntityJsonMapper {
    private final Gson gson;

    public UserEntityJsonMapper() {
        this.gson = new Gson();
    }

    public UserEntity transformUserEntity(String userJsonRespone) {
        return gson.fromJson(userJsonRespone, UserEntity.class);
    }
}
