package io.github.putme2yourheart.cleanarchitecture.data.cache;

import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.reactivex.Observable;

public interface UserCache {

    /**
     * 从缓存获取用户信息
     *
     * @param user 用户名   
     */
    Observable<UserEntity> get(final String user);

    /**
     * 将用户信息写入缓存
     *
     * @param userEntity 写入对象
     */
    void put(UserEntity userEntity);

    /**
     * 检查用户是否已经有缓存了
     *
     * @param user 通过用户名寻找用户缓存
     * @return 有缓存则返回true
     */
    boolean isCached(final String user);

    /**
     * 缓存是否过期
     *
     * @return true 过期   false 没过期
     */
    boolean isExpired(String user);

}
