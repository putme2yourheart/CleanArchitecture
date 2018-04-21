package io.github.putme2yourheart.cleanarchitecture.data.repository.datasource;

import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.reactivex.Observable;

public interface UserDataStore {
    Observable<UserEntity> userEntityDetails(final String user);
}
