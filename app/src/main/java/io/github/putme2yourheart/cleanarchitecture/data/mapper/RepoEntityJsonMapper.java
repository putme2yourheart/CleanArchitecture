package io.github.putme2yourheart.cleanarchitecture.data.mapper;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;

public class RepoEntityJsonMapper {
    private final Gson gson;

    public RepoEntityJsonMapper() {
        gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                // 忽略private字段
                return f.getName().equals("private");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz.getName().equals("license");
            }
        }).create();
    }

    public List<RepoEntity> transformRepoEntity(String repoJsonResponse) {
        final Type listOfRepoEntityType = new TypeToken<List<RepoEntity>>() {}.getType();
        return gson.fromJson(repoJsonResponse, listOfRepoEntityType);
    }
}
