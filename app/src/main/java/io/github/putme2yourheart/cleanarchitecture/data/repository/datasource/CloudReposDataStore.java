package io.github.putme2yourheart.cleanarchitecture.data.repository.datasource;

import java.util.List;

import javax.inject.Inject;

import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApi;
import io.reactivex.Observable;

public class CloudReposDataStore implements ReposDataStore {
    private RestApi restApi;

    @Inject
    public CloudReposDataStore(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<RepoEntity>> repoEntityList(String user) {
        return restApi.getRepoList(user);
    }
}
