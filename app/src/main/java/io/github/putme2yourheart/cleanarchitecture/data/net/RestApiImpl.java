package io.github.putme2yourheart.cleanarchitecture.data.net;

import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.RepoEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.UserEntityJsonMapper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.io.IOException;
import java.util.List;

public class RestApiImpl implements RestApi {
    private final UserEntityJsonMapper userEntityJsonMapper;
    private final RepoEntityJsonMapper repoEntityJsonMapper;

    public RestApiImpl(UserEntityJsonMapper userEntityJsonMapper, RepoEntityJsonMapper repoEntityJsonMapper) {
        this.userEntityJsonMapper = userEntityJsonMapper;
        this.repoEntityJsonMapper = repoEntityJsonMapper;
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
                    // aa
                    emitter.onError(new Exception());
                }
                emitter.onComplete();
            }
        });
    }

    @Override
    public Observable<List<RepoEntity>> getRepoList(final String user) {
        //return Observable.create(new ObservableOnSubscribe<List<RepoEntity>>() {
        //    @Override
        //    public void subscribe(ObservableEmitter<List<RepoEntity>> emitter) throws Exception {
                //String repoEntitiesJson = getReposFromApi(user);
                //if (repoEntitiesJson != null) {
                //    emitter.onNext(repoEntityJsonMapper.transformRepoEntity(repoEntitiesJson));
                //} else {
                //    emitter.onError(new Exception());
                //}
                //emitter.onComplete();
            //}
        //});
        GithubService githubService = ApiService.create(RestApi.GITHUB_RUL).create(GithubService.class);
        return githubService.listReposRx(user);
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

    private String getReposFromApi(String user) {
        GithubService githubService = ApiService.create(RestApi.GITHUB_RUL).create(GithubService.class);
        try {
            return githubService.listRepos(user).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
