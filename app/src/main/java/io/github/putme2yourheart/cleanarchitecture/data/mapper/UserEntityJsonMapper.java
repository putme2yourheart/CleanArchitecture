package io.github.putme2yourheart.cleanarchitecture.data.mapper;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;

public class UserEntityJsonMapper {
    private final Gson gson;

    public UserEntityJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * 将json数据转换成UserEntity实体
     *
     * @param userJsonResponse json数据
     * @return UserEntity
     */
    public UserEntity transformUserEntity(String userJsonResponse) {
        return gson.fromJson(userJsonResponse, UserEntity.class);
    }
}
