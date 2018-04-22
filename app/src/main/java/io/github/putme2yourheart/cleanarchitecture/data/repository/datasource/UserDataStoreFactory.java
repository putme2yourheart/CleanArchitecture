package io.github.putme2yourheart.cleanarchitecture.data.repository.datasource;

import javax.inject.Inject;

import io.github.putme2yourheart.cleanarchitecture.data.cache.UserCache;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.UserEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApi;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApiImpl;

public class UserDataStoreFactory {
    private UserCache userCache;
    private RestApi restApi;

    @Inject
    public UserDataStoreFactory(UserCache userCache, RestApi restApi) {
        this.userCache = userCache;
        this.restApi = restApi;
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
        return new CloudUserDataStore(restApi, userCache);
    }
}
