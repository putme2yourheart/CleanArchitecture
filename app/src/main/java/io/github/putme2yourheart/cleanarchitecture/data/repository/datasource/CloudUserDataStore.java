package io.github.putme2yourheart.cleanarchitecture.data.repository.datasource;

import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApi;
import io.reactivex.Observable;

public class CloudUserDataStore implements UserDataStore {
    private RestApi restApi;

    public CloudUserDataStore(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<UserEntity> userEntityDetails(String user) {
        return restApi.userEntity(user);
    }
}
