package io.github.putme2yourheart.cleanarchitecture.data.net;

import java.io.IOException;
import java.util.List;

import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.UserEntityJsonMapper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RestApiImpl implements RestApi {
    private final UserEntityJsonMapper userEntityJsonMapper;

    public RestApiImpl(UserEntityJsonMapper userEntityJsonMapper) {
        this.userEntityJsonMapper = userEntityJsonMapper;
    }

    private String getUserDetailsFromApi(String user) {
        try {
           GithubService githubService =  ApiService.create(RestApi.GITHUB_RUL).create(GithubService.class);
           return githubService.getUserEntity(user).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Observable<UserEntity> userEntity(final String user) {
        return Observable.create(new ObservableOnSubscribe<UserEntity>() {
            @Override
            public void subscribe(ObservableEmitter<UserEntity> emitter) throws Exception {
                String userEntityJson = getUserDetailsFromApi(user);
                if (userEntityJson != null) {
                    emitter.onNext(userEntityJsonMapper.transformUserEntity(userEntityJson));
                } else {
                    emitter.onError(new Exception());
                }
                emitter.onComplete();
            }
        });
    }

    @Override
    public Observable<List<RepoEntity>> getRepoList(String user) {
        return null;
    }
}
