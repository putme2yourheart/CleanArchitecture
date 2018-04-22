package io.github.putme2yourheart.cleanarchitecture.data.repository.datasource;

import android.util.Log;

import io.github.putme2yourheart.cleanarchitecture.data.cache.UserCache;
import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.reactivex.Observable;

public class DiskUserDataStore implements UserDataStore {
    private UserCache userCache;

    DiskUserDataStore(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public Observable<UserEntity> userEntityDetails(String user) {
        Log.e("load", "disk");
        return userCache.get(user);
    }
}
