package io.github.putme2yourheart.cleanarchitecture.data.repository.datasource;

import io.github.putme2yourheart.cleanarchitecture.data.cache.UserCache;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.UserEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApi;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApiImpl;

public class UserDataStoreFactory {
    private UserCache userCache;

    public UserDataStoreFactory(UserCache userCache) {
        this.userCache = userCache;
    }

    /**
     * 工厂类根据是否有本地缓存创建UserDataStore
     *
     * @param user 用户名
     * @return UserDataStore
     */
    public UserDataStore create(String user) {
        if (userCache.isCached(user) && !userCache.isExpired(user)) {
            return new DiskUserDataStore(userCache);
        } else {
            return createCloudDataStore();
        }
    }

    /**
     * 创建从网络上获取用户信息的UserDataStore
     *
     * @return UserDataStore
     */
    private UserDataStore createCloudDataStore() {
        final UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
        final RestApi restApi = new RestApiImpl(userEntityJsonMapper);
        return new CloudUserDataStore(restApi, userCache);
    }
}
