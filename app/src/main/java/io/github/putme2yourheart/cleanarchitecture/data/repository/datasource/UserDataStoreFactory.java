package io.github.putme2yourheart.cleanarchitecture.data.repository.datasource;

import io.github.putme2yourheart.cleanarchitecture.data.mapper.UserEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApi;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApiImpl;

public class UserDataStoreFactory {

    public UserDataStoreFactory() {
    }

    /**
     * 创建从网络上获取用户信息的UserDataStore
     *
     * @return UserDataStore
     */
    public UserDataStore createCloudDataStore() {
        final UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
        final RestApi restApi = new RestApiImpl(userEntityJsonMapper);
        return new CloudUserDataStore(restApi);
    }
}
