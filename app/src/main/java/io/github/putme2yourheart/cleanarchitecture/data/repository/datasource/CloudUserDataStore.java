package io.github.putme2yourheart.cleanarchitecture.data.repository.datasource;

import android.util.Log;

import io.github.putme2yourheart.cleanarchitecture.data.cache.UserCache;
import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApi;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class CloudUserDataStore implements UserDataStore {
    private RestApi restApi;
    private UserCache userCache;

    CloudUserDataStore(RestApi restApi, UserCache userCache) {
        this.restApi = restApi;
        this.userCache = userCache;
    }

    @Override
    public Observable<UserEntity> userEntityDetails(String user) {
        Log.e("load", "cloud");
        return restApi.userEntity(user).doOnNext(new Consumer<UserEntity>() {
            @Override
            public void accept(UserEntity userEntity) throws Exception {
                userCache.put(userEntity);
            }
        });
    }
}
